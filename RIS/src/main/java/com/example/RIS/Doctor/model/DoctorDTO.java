package com.example.RIS.Doctor.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DoctorDTO {
    @NotNull(groups = {DoctorDTO.Modify.class, DoctorDTO.ChangeStatus.class}, message = "El ID no puede ser nulo")
    private Long id;

    @NotBlank(groups = {DoctorDTO.Register.class, DoctorDTO.Modify.class}, message = "El nombre no puede estar vacío")
    private String name;

    @NotBlank(groups = {DoctorDTO.Register.class, DoctorDTO.Modify.class}, message = "El correo electrónico no puede estar vacío")
    @Email(groups = {DoctorDTO.Register.class, DoctorDTO.Modify.class}, message = "El correo electrónico no tiene un formato válido")
    private String email;

    @NotNull(groups = {DoctorDTO.ChangeStatus.class}, message = "El estado no puede ser nulo")
    private Boolean status;

    public DoctorDTO() {

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public interface Register {}
    public interface Modify {}
    public interface ChangeStatus {}
}
