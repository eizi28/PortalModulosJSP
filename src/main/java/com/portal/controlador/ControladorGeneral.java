package com.portal.controlador;

import com.portal.modelo.Usuario;
import com.portal.servicio.ServicioUsuarios;
import com.portal.servicio.ServicioModulos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = {
    "/ingresar",
    "/registrar",
    "/panel",
    "/salir"
})
public class ControladorGeneral extends HttpServlet {

    private final ServicioUsuarios servicioUsuarios =
            new ServicioUsuarios();

    private final ServicioModulos servicioModulos =
            new ServicioModulos();

    // PETICIONES GET
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String ruta = request.getServletPath();

        switch (ruta) {

            case "/ingresar":
                if (usuarioActual(request) != null) {
                    response.sendRedirect(
                        request.getContextPath() + "/panel"
                    );
                    return;
                }

                request.getRequestDispatcher("/index.jsp")
                       .forward(request, response);
                break;

            case "/registrar":
                request.getRequestDispatcher("/registro.jsp")
                       .forward(request, response);
                break;

            case "/panel":
                mostrarPanel(request, response);
                break;

            default:
                response.sendRedirect(
                    request.getContextPath() + "/ingresar"
                );
        }
    }

    // PETICIONES POST
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String ruta = request.getServletPath();

        switch (ruta) {

            case "/registrar":
                registrar(request, response);
                break;

            case "/ingresar":
                iniciarSesion(request, response);
                break;

            case "/salir":
                cerrarSesion(request, response);
                break;

            default:
                response.sendError(
                    HttpServletResponse.SC_METHOD_NOT_ALLOWED
                );
        }
    }

    // 1. REGISTRAR USUARIO
    private void registrar(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");
        String confirmar = request.getParameter("confirmarClave");

        try {
            if (clave == null || !clave.equals(confirmar)) {
                throw new IllegalArgumentException(
                    "Las contrasenas no coinciden."
                );
            }

            boolean registrado = servicioUsuarios.registrar(
                nombre, correo, clave
            );

            if (!registrado) {
                throw new IllegalArgumentException(
                    "El correo ya esta registrado."
                );
            }

            response.sendRedirect(
                request.getContextPath()
                + "/ingresar?registro=ok"
            );

        } catch (IllegalArgumentException e) {

            request.setAttribute("error", e.getMessage());
            request.setAttribute("nombre", nombre);
            request.setAttribute("correo", correo);

            request.getRequestDispatcher("/registro.jsp")
                   .forward(request, response);
        }
    }

    // 2. INICIAR SESION
    private void iniciarSesion(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");

        Usuario usuario = servicioUsuarios.iniciarSesion(
            correo, clave
        );

        if (usuario == null) {

            request.setAttribute(
                "error",
                "Correo o contrasena incorrectos."
            );

            request.getRequestDispatcher("/index.jsp")
                   .forward(request, response);

            return;
        }

        HttpSession sesion = request.getSession(true);

        // Renovar el identificador al autenticarse
        request.changeSessionId();

        sesion.setAttribute("usuario", usuario);

        response.sendRedirect(
            request.getContextPath() + "/panel"
        );
    }

    // 3. MOSTRAR PANEL PRINCIPAL
    private void mostrarPanel(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = usuarioActual(request);

        if (usuario == null) {
            response.sendRedirect(
                request.getContextPath() + "/ingresar"
            );
            return;
        }

        request.setAttribute(
            "modulos",
            servicioModulos.obtenerModulos()
        );

        request.setAttribute(
            "cantidadModulos",
            servicioModulos.contarModulos()
        );

        request.getRequestDispatcher(
            "/WEB-INF/vistas/dashboard.jsp"
        ).forward(request, response);
    }

    // 4. CERRAR SESION
    private void cerrarSesion(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        HttpSession sesion = request.getSession(false);

        if (sesion != null) {
            sesion.invalidate();
        }

        response.sendRedirect(
            request.getContextPath() + "/ingresar"
        );
    }

    // 5. CONSULTAR USUARIO AUTENTICADO
    private Usuario usuarioActual(
            HttpServletRequest request) {

        HttpSession sesion = request.getSession(false);

        if (sesion == null) {
            return null;
        }

        Object dato = sesion.getAttribute("usuario");

        return dato instanceof Usuario
                ? (Usuario) dato
                : null;
    }
}
