package com.example.RIS.Doctor.model;

import jakarta.persistence.*;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", columnDefinition = "VARCHAR(30)")
    private String name;
    @Column(name = "email", columnDefinition = "VARCHAR(40)")
    private String email;
    @Column(name = "status", columnDefinition = "BOOL DEFAULT TRUE")
    private boolean status;


    public Doctor() {

    }

    public Doctor(Long id, String name, String email, boolean status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}