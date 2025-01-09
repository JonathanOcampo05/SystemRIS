package com.example.RIS.Patient.model;

import jakarta.persistence.*;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", columnDefinition = "VARCHAR(30)")
    private String name;
    @Column(name = "email", columnDefinition = "VARCHAR(40)")
    private String email;
    @Column(name = "age", columnDefinition = "INT")
    private int age;
    @Column(name = "turn", columnDefinition = "INT")
    private int turn;
    @Column(name = "symptoms", columnDefinition = "VARCHAR(100)")
    private String symptoms;
    @Column(name = "supplies", columnDefinition = "VARCHAR(100)")
    private String supplies;
    @Column(name = "status", columnDefinition = "BOOL DEFAULT TRUE")
    private boolean status;


    public Patient() {

    }

    public Patient(Long id, String name, String email, int age, int turn, String symptoms, String supplies, boolean status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.turn = turn;
        this.symptoms = symptoms;
        this.supplies = supplies;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getSupplies() {
        return supplies;
    }

    public void setSupplies(String supplies) {
        this.supplies = supplies;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}