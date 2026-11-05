package com.portal.modelo;

public class Usuario {

    private final String nombre;
    private final String correo;
    private final String passwordHash;
    private final String rol;

    // CONSTRUCTOR
    public Usuario(String nombre, String correo,
                   String passwordHash) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El nombre no puede estar vacio."
            );
        }

        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El correo no puede estar vacio."
            );
        }

        if (passwordHash == null || passwordHash.isEmpty()) {
            throw new IllegalArgumentException(
                "La clave protegida no puede estar vacia."
            );
        }

        this.nombre = nombre.trim();
        this.correo = correo.trim().toLowerCase(
            java.util.Locale.ROOT
        );
        this.passwordHash = passwordHash;
        this.rol = "estudiante";
    }

    // OBTENER NOMBRE
    public String getNombre() {
        return nombre;
    }

    // OBTENER CORREO
    public String getCorreo() {
        return correo;
    }

    // OBTENER HASH DE LA CONTRASENA
    public String getPasswordHash() {
        return passwordHash;
    }

    // OBTENER ROL
    public String getRol() {
        return rol;
    }

    // MOSTRAR INFORMACION PUBLICA
    @Override
    public String toString() {
        return "Usuario: " + nombre
                + " | Correo: " + correo
                + " | Rol: " + rol;
    }
}
