package com.portal.modelo;

public class Modulo {

    private final int id;
    private final String nombre;
    private final String descripcion;
    private final String categoria;

    // CONSTRUCTOR
    public Modulo(int id, String nombre,
                  String descripcion, String categoria) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser mayor que cero."
            );
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El nombre del modulo es obligatorio."
            );
        }

        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "La descripcion es obligatoria."
            );
        }

        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "La categoria es obligatoria."
            );
        }

        this.id = id;
        this.nombre = nombre.trim();
        this.descripcion = descripcion.trim();
        this.categoria = categoria.trim();
    }

    // CONSULTAR ID
    public int getId() {
        return id;
    }

    // CONSULTAR NOMBRE
    public String getNombre() {
        return nombre;
    }

    // CONSULTAR DESCRIPCION
    public String getDescripcion() {
        return descripcion;
    }

    // CONSULTAR CATEGORIA
    public String getCategoria() {
        return categoria;
    }

    // MOSTRAR INFORMACION
    @Override
    public String toString() {
        return "Modulo " + id
                + ": " + nombre
                + " | Categoria: " + categoria
                + " | Descripcion: " + descripcion;
    }
}
