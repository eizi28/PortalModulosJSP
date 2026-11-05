package com.portal.servicio;

import com.portal.modelo.Modulo;
import java.util.List;

public class ServicioModulos {

    private final List<Modulo> modulos;

    // CONSTRUCTOR
    public ServicioModulos() {

        modulos = List.of(

            new Modulo(
                1,
                "HTML",
                "Aprende a crear la estructura de una pagina web.",
                "Desarrollo web"
            ),

            new Modulo(
                2,
                "CSS",
                "Aprende a personalizar colores y estilos.",
                "Diseno web"
            ),

            new Modulo(
                3,
                "JavaScript",
                "Aprende a crear paginas interactivas.",
                "Programacion web"
            ),

            new Modulo(
                4,
                "Java",
                "Aprende los fundamentos de la programacion.",
                "Programacion"
            ),

            new Modulo(
                5,
                "Bases de Datos",
                "Conoce como almacenar y consultar informacion.",
                "Datos"
            ),

            new Modulo(
                6,
                "JSP y Servlets",
                "Construye aplicaciones web utilizando Java.",
                "Desarrollo web"
            )
        );
    }

    // 1. OBTENER TODOS LOS MODULOS
    public List<Modulo> obtenerModulos() {
        return modulos;
    }

    // 2. CONSULTAR CANTIDAD DE MODULOS
    public int contarModulos() {
        return modulos.size();
    }

    // 3. BUSCAR UN MODULO POR ID
    public Modulo buscarPorId(int id) {

        for (Modulo modulo : modulos) {

            if (modulo.getId() == id) {
                return modulo;
            }
        }

        return null;
    }

    // 4. FILTRAR MODULOS POR CATEGORIA
    public List<Modulo> filtrarPorCategoria(
            String categoria) {

        if (categoria == null) {
            return List.of();
        }

        return modulos.stream()
            .filter(modulo ->
                modulo.getCategoria().equalsIgnoreCase(
                    categoria.trim()
                )
            )
            .toList();
    }
}
