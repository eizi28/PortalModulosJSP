package com.portal.servicio;

import com.portal.modelo.Usuario;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.GeneralSecurityException;

import java.util.Base64;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class ServicioUsuarios {

    // Usuarios almacenados temporalmente
    private final Map<String, Usuario> usuarios =
            new ConcurrentHashMap<>();

    // Configuracion de seguridad
    private static final int ITERACIONES = 210000;
    private static final int TAMANO_SAL = 16;
    private static final int TAMANO_HASH = 256;

    private final SecureRandom aleatorio = new SecureRandom();

    // 1. REGISTRAR UN USUARIO
    public boolean registrar(
            String nombre,
            String correo,
            String clave) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El nombre es obligatorio."
            );
        }

        if (correo == null) {
            throw new IllegalArgumentException(
                "El correo es obligatorio."
            );
        }

        String correoNormalizado =
                correo.trim().toLowerCase(Locale.ROOT);

        if (!correoNormalizado.matches(
                "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new IllegalArgumentException(
                "El correo no tiene un formato valido."
            );
        }

        if (clave == null || clave.length() < 8) {
            throw new IllegalArgumentException(
                "La contrasena debe tener minimo 8 caracteres."
            );
        }

        String hash = protegerClave(clave);

        Usuario nuevo = new Usuario(
            nombre,
            correoNormalizado,
            hash
        );

        // Evita registrar dos veces el mismo correo
        return usuarios.putIfAbsent(
            correoNormalizado, nuevo
        ) == null;
    }

    // 2. INICIAR SESION
    public Usuario iniciarSesion(
            String correo,
            String clave) {

        if (correo == null || clave == null) {
            return null;
        }

        String correoNormalizado =
                correo.trim().toLowerCase(Locale.ROOT);

        Usuario encontrado =
                usuarios.get(correoNormalizado);

        if (encontrado == null) {
            return null;
        }

        boolean correcta = verificarClave(
            clave, encontrado.getPasswordHash()
        );

        return correcta ? encontrado : null;
    }

    // 3. CONSULTAR CANTIDAD DE USUARIOS
    public int obtenerCantidadUsuarios() {
        return usuarios.size();
    }

    // 4. PROTEGER LA CONTRASENA
    private String protegerClave(String clave) {

        byte[] sal = new byte[TAMANO_SAL];
        aleatorio.nextBytes(sal);

        byte[] hash = derivarClave(clave, sal);

        return Base64.getEncoder().encodeToString(sal)
                + ":"
                + Base64.getEncoder().encodeToString(hash);
    }

    // 5. VERIFICAR LA CONTRASENA
    private boolean verificarClave(
            String clave, String valorGuardado) {

        try {
            String[] partes = valorGuardado.split(":");

            if (partes.length != 2) {
                return false;
            }

            byte[] sal = Base64.getDecoder()
                    .decode(partes[0]);

            byte[] hashGuardado = Base64.getDecoder()
                    .decode(partes[1]);

            byte[] hashIngresado =
                    derivarClave(clave, sal);

            return MessageDigest.isEqual(
                hashGuardado, hashIngresado
            );

        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // 6. GENERAR HASH CON PBKDF2
    private byte[] derivarClave(
            String clave, byte[] sal) {

        PBEKeySpec especificacion = new PBEKeySpec(
            clave.toCharArray(),
            sal,
            ITERACIONES,
            TAMANO_HASH
        );

        try {
            SecretKeyFactory fabrica =
                SecretKeyFactory.getInstance(
                    "PBKDF2WithHmacSHA256"
                );

            return fabrica.generateSecret(
                especificacion
            ).getEncoded();

        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(
                "Error al proteger la contrasena.", e
            );

        } finally {
            especificacion.clearPassword();
        }
    }
}
