package com.laboratorio.CRUD.Entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Animes {
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCapitulos() {
        return capitulos;
    }

    public String getEstado() {
        return estado;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCapitulos(String capitulos) {
        this.capitulos = capitulos;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
