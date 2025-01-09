package com.example.RIS.insumos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "insumo")
public class Insumos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_insumo")
    private Long id_insumo;

    @Column(name = "nombre", columnDefinition = "VARCHAR(50)")
    private String nombre;

    @Column(name = "tipo", columnDefinition = "VARCHAR(50)")
    private String tipo;

    @Column(name = "descripcion", columnDefinition = "VARCHAR(100)")
    private String descripcion;

    @Column(name = "cantidad", columnDefinition = "INT")
    private int cantidad;

    @Column(name ="status", columnDefinition = "BOOLEAN")
    private boolean status;


    public Insumos() {}

    public Insumos(Long id_insumo, String nombre, String tipo, String descripcion, int cantidad, boolean status) {
        this.id_insumo = id_insumo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.status = status;
    }

    public Insumos(String nombre, String tipo, String descripcion, int cantidad, boolean status) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.status = status;
    }

    public Long getId_insumo() {
        return id_insumo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public boolean isStatus() {
        return status;
    }

    public void setId_insumo(Long id_insumo) {
        this.id_insumo = id_insumo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
