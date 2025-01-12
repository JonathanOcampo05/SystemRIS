package com.example.RIS.Patient.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PatientDTO {
    @NotNull(groups = {PatientDTO.Modify.class, PatientDTO.ChangeStatus.class}, message = "El ID no puede ser nulo")
    private Long id;

    @NotBlank(groups = {PatientDTO.Register.class, PatientDTO.Modify.class}, message = "El nombre no puede estar vacío")
    @Size(max = 30, message = "El nombre no puede tener más de 30 caracteres")
    private String name;

    @NotBlank(groups = {PatientDTO.Register.class, PatientDTO.Modify.class}, message = "El correo electrónico no puede estar vacío")
    @Email(groups = {PatientDTO.Register.class, PatientDTO.Modify.class}, message = "El correo electrónico no tiene un formato válido")
    @Size(max = 40, message = "El correo no puede tener más de 40 caracteres")
    private String email;

    @NotNull(groups = {PatientDTO.Register.class, PatientDTO.Modify.class}, message = "La edad no puede ser nula")
    @Min(value = 0, message = "La edad debe ser mayor o igual a 0")
    @Max(value = 150, message = "La edad debe ser menor o igual a 150")
    private int age;

    @NotNull(groups = {PatientDTO.Register.class, PatientDTO.Modify.class}, message = "El turno no puede ser nulo")
    @Min(value = 1, message = "El turno debe ser al menos 1")
    private int turn;

    @NotBlank(groups = {PatientDTO.Register.class, PatientDTO.Modify.class}, message = "Los síntomas no pueden estar vacíos")
    @Size(max = 100, message = "Los síntomas no pueden tener más de 100 caracteres")
    private String symptoms;

    @NotBlank(groups = {PatientDTO.Register.class, PatientDTO.Modify.class}, message = "Los suministros no pueden estar vacíos")
    @Size(max = 100, message = "Los suministros no pueden tener más de 100 caracteres")
    private String supplies;

     @NotBlank
     private String doctor;

    @NotNull(groups = {PatientDTO.ChangeStatus.class}, message = "El estado no puede ser nulo")
    private Boolean status;

    public PatientDTO() {
    }

    // Getters y Setters
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    // Interfaces para validación por grupos
    public interface Register {}
    public interface Modify {}
    public interface ChangeStatus {}
}
