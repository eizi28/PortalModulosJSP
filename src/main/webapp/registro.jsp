<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%
    // COMPROBAR SI EL USUARIO YA INICIO SESION
    if (session.getAttribute("usuario") != null) {
        response.sendRedirect(
            request.getContextPath() + "/panel"
        );
        return;
    }

    // DETECTAR ERRORES DE REGISTRO
    boolean mostrarError =
        request.getAttribute("error") != null;

    String ruta = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Crear cuenta | Portal Educativo</title>

    <link rel="stylesheet"
          href="<%= ruta %>/css/estilos.css">
</head>

<body>

    <!-- ENCABEZADO -->
    <header class="encabezado">

        <div class="contenedor barra">

            <a href="<%= ruta %>/ingresar"
               class="logo">
                Portal Educativo
            </a>

            <a href="<%= ruta %>/ingresar"
               class="enlace-registro">
                Iniciar sesion
            </a>

        </div>

    </header>

    <!-- FORMULARIO DE REGISTRO -->
    <main class="pagina-acceso">

        <section class="tarjeta-acceso">

            <div class="icono-acceso">PE</div>

            <h1>Crea tu cuenta</h1>

            <p class="subtitulo">
                Registrate para acceder a nuestros
                modulos de aprendizaje.
            </p>

            <!-- CONDICIONAL JSP PARA ERRORES -->
            <% if (mostrarError) { %>

                <div class="alerta error" role="alert">
                    No fue posible completar el registro.
                    Comprueba que el correo no este
                    registrado, que la contrasena tenga
                    al menos 8 caracteres y que
                    ambas contrasenas coincidan.
                </div>

            <% } %>

            <form method="POST"
                  action="<%= ruta %>/registrar">

                <!-- NOMBRE -->
                <div class="grupo-formulario">

                    <label for="nombre">
                        Nombre completo
                    </label>

                    <input
                        type="text"
                        id="nombre"
                        name="nombre"
                        placeholder="Escribe tu nombre"
                        autocomplete="name"
                        maxlength="100"
                        required>

                </div>

                <!-- CORREO -->
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

                <!-- CONTRASENA -->
                <div class="grupo-formulario">

                    <label for="clave">
                        Contrasena
                    </label>

                    <input
                        type="password"
                        id="clave"
                        name="clave"
                        placeholder="Minimo 8 caracteres"
                        autocomplete="new-password"
                        minlength="8"
                        required>

                </div>

                <!-- CONFIRMAR CONTRASENA -->
                <div class="grupo-formulario">

                    <label for="confirmarClave">
                        Confirmar contrasena
                    </label>

                    <input
                        type="password"
                        id="confirmarClave"
                        name="confirmarClave"
                        placeholder="Repite tu contrasena"
                        autocomplete="new-password"
                        minlength="8"
                        required>

                </div>

                <!-- ENVIAR FORMULARIO -->
                <button type="submit"
                        class="boton-principal">
                    Crear cuenta
                </button>

            </form>

            <p class="texto-registro">
                ¿Ya tienes una cuenta?

                <a href="<%= ruta %>/ingresar">
                    Inicia sesion aqui
                </a>
            </p>

        </section>

    </main>

    <!-- PIE DE PAGINA -->
    <footer class="pie-pagina">

        <p>
            Portal Educativo -
            Proyecto academico JSP y Java
        </p>

    </footer>

</body>
</html>
