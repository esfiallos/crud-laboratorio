package com.laboratorio.CRUD.Entidades;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Representa la entidad de un Anime en la base de datos.
 * Contiene información básica sobre un anime como su nombre, descripción, categoría,
 * número de capítulos, estado de emisión y valoración por los usuarios.
 */
@Entity
@Data
public class Animes {

    /**
     * Identificador único del anime.
     * Este campo es generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    private String nombre;
    @Basic
    private String descripcion;
    @Basic
    private String categoria;
    @Basic
    private String capitulos;
    @Basic
    private String estado;
    @Basic
    private int valoracion;

}
