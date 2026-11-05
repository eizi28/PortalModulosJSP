<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%
    // COMPROBAR SI YA EXISTE UNA SESION
    if (session.getAttribute("usuario") != null) {
        response.sendRedirect(
            request.getContextPath() + "/panel"
        );
        return;
    }

    // CONDICIONALES PARA MOSTRAR MENSAJES
    boolean mostrarError =
        request.getAttribute("error") != null;

    boolean registroExitoso =
        "ok".equals(request.getParameter("registro"));

    String ruta = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Iniciar Sesion | Portal Educativo</title>

    <link rel="stylesheet"
          href="<%= ruta %>/css/estilos.css">
</head>

<body>

    <!-- ENCABEZADO -->
    <header class="encabezado">

        <div class="contenedor barra">
            <a class="logo" href="<%= ruta %>/ingresar">
                Portal Educativo
            </a>

            <a class="enlace-registro"
               href="<%= ruta %>/registrar">
                Crear cuenta
            </a>
        </div>

    </header>

    <main class="pagina-acceso">

        <section class="tarjeta-acceso">

            <div class="icono-acceso">PE</div>

            <h1>Bienvenido nuevamente</h1>

            <p class="subtitulo">
                Ingresa a tu cuenta para explorar
                nuestros modulos de aprendizaje.
            </p>

            <!-- CONDICIONAL JSP: REGISTRO EXITOSO -->
            <% if (registroExitoso) { %>

                <div class="alerta exito">
                    Cuenta creada correctamente.
                    Ya puedes iniciar sesion.
                </div>

            <% } %>

            <!-- CONDICIONAL JSP: ERROR -->
            <% if (mostrarError) { %>

                <div class="alerta error" role="alert">
                    Correo o contrasena incorrectos.
                    Verifica tus datos e intenta nuevamente.
                </div>

            <% } %>

            <!-- FORMULARIO DE INICIO DE SESION -->
            <form method="POST"
                  action="<%= ruta %>/ingresar">

                <div class="grupo-formulario">

                    <label for="correo">
                        Correo electronico
                    </label>

                    <input
                        type="email"
                        id="correo"
                        name="correo"
                        placeholder="ejemplo@correo.com"
                        autocomplete="email"
                        required>

                </div>

                <div class="grupo-formulario">

                    <label for="clave">
                        Contrasena
                    </label>

                    <input
                        type="password"
                        id="clave"
                        name="clave"
                        placeholder="Ingresa tu contrasena"
                        autocomplete="current-password"
                        required>

                </div>

                <button type="submit"
                        class="boton-principal">
                    Iniciar sesion
                </button>

            </form>

            <p class="texto-registro">
                ¿Todavia no tienes cuenta?

                <a href="<%= ruta %>/registrar">
                    Registrate aqui
                </a>
            </p>

        </section>

    </main>

    <footer class="pie-pagina">
        <p>
            Portal Educativo -
            Proyecto academico JSP y Java
        </p>
    </footer>

</body>
</html>
