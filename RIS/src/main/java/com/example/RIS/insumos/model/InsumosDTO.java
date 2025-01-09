package com.example.RIS.insumos.model;

import org.antlr.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.NotBlank;

public class InsumosDTO{

    @NotNull
    private Long id_insumo;

    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;

    @NotBlank
    private String tipo;

    @NotNull
    private int cantidad;

    private boolean estado;

    public Long getId_insumo() {
        return id_insumo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setId_insumo(Long id_insumo) {
        this.id_insumo = id_insumo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public interface Register {}
    public interface Modify {}
    public interface ChangeStatus {}
}
