package com.example.RIS.Report.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReportDTO {
    @NotNull(groups = {ReportDTO.Modify.class, ReportDTO.ChangeStatus.class}, message = "El ID no puede ser nulo")
    private Long id;

    @NotBlank(groups = {ReportDTO.Register.class, ReportDTO.Modify.class}, message = "El nombre no puede estar vacío")
    @Size(max = 30, message = "El nombre no puede tener más de 30 caracteres")
    private String name;

    @NotBlank(groups = {ReportDTO.Register.class, ReportDTO.Modify.class}, message = "La descripción no puede estar vacía")
    @Size(max = 100, message = "La descripción no puede tener más de 100 caracteres")
    private String description;

    @NotNull(groups = {ReportDTO.ChangeStatus.class}, message = "El estado no puede ser nulo")
    private Boolean status;

    public ReportDTO() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    // Interfaces para validación por grupos
    public interface Register {}
    public interface Modify {}
    public interface ChangeStatus {}
}

