<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ page import="com.portal.modelo.Usuario" %>
<%@ page import="com.portal.modelo.Modulo" %>
<%@ page import="java.util.List" %>

<%!
    // Evitar que los textos se interpreten como HTML
    private String escapar(String texto) {
        if (texto == null) {
            return "";
        }

        return texto.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
%>

<%
    // 1. COMPROBAR SESION DEL USUARIO
    Usuario usuario =
        (Usuario) session.getAttribute("usuario");

    if (usuario == null) {
        response.sendRedirect(
            request.getContextPath() + "/ingresar"
        );
        return;
    }

    // 2. OBTENER MODULOS DESDE EL CONTROLADOR
    List<?> modulos =
        (List<?>) request.getAttribute("modulos");

    if (modulos == null) {
        modulos = java.util.Collections.emptyList();
    }

    String ruta = request.getContextPath();
    int cantidad = modulos.size();
%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Panel principal | Portal Educativo</title>

    <link rel="stylesheet"
          href="<%= ruta %>/css/estilos.css">
</head>

<body>

    <!-- ENCABEZADO PRINCIPAL -->
    <header class="encabezado">

        <div class="contenedor barra">

            <a href="<%= ruta %>/panel" class="logo">
                Portal Educativo
            </a>

            <form method="POST"
                  action="<%= ruta %>/salir">

                <button type="submit"
                        class="boton-salir">
                    Cerrar sesion
                </button>

            </form>

        </div>

    </header>

    <main class="panel-contenedor">

        <!-- BIENVENIDA -->
        <section class="panel-bienvenida">

            <p class="etiqueta-panel">
                PANEL DEL ESTUDIANTE
            </p>

            <h1>
                ¡Bienvenido,
                <%= escapar(usuario.getNombre()) %>!
            </h1>

            <p>
                Explora nuestros modulos y fortalece
                tus conocimientos de programacion.
            </p>

        </section>

        <!-- INFORMACION DEL USUARIO -->
        <section class="panel-resumen">

            <div class="tarjeta-resumen">

                <h3>Usuario registrado</h3>

                <p>
                    <%= escapar(usuario.getCorreo()) %>
                </p>

            </div>

            <div class="tarjeta-resumen">

                <h3>Modulos disponibles</h3>

                <p class="numero-resumen">
                    <%= cantidad %>
                </p>

            </div>

        </section>

        <!-- MODULOS EDUCATIVOS -->
        <section class="seccion-modulos" id="modulos">

            <h2>Mis modulos de aprendizaje</h2>

            <p class="descripcion-modulos">
                Conoce los contenidos que ofrece
                nuestro portal educativo.
            </p>

            <!-- CONDICIONAL JSP -->
            <% if (modulos.isEmpty()) { %>

                <div class="mensaje-vacio">
                    No hay modulos disponibles.
                </div>

            <% } else { %>

                <div class="grid-modulos">

                    <!-- BUCLE JSP -->
                    <% for (Object elemento : modulos) {

                        if (!(elemento instanceof Modulo)) {
                            continue;
                        }

                        Modulo modulo = (Modulo) elemento;
                    %>

                        <article class="modulo-card">

                            <span class="numero-modulo">
                                MODULO <%= modulo.getId() %>
                            </span>

                            <h3>
                                <%= escapar(modulo.getNombre()) %>
                            </h3>

                            <p>
                                <%= escapar(modulo.getDescripcion()) %>
                            </p>

                            <!-- CONDICIONAL POR CATEGORIA -->
                            <% if ("Desarrollo web".equalsIgnoreCase(
                                    modulo.getCategoria())) { %>

                                <span class="categoria destacada">
                                    Desarrollo web
                                </span>

                            <% } else { %>

                                <span class="categoria">
                                    <%= escapar(
                                        modulo.getCategoria()
                                    ) %>
                                </span>

                            <% } %>

                        </article>

                    <% } %>

                </div>

            <% } %>

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
