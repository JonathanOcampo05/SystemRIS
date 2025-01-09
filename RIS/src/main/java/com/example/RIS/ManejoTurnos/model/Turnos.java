package com.example.RIS.ManejoTurnos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "manejoTurnos")
public class Turnos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "status", columnDefinition = "BOOL DEFAULT TRUE")
    private boolean status;


    public Turnos() {

    }

    public Turnos(Long id, boolean status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}