package com.example.RIS.Patient.model;

import com.example.RIS.insumos.model.Insumos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAll();
    List<Patient> findByStatusTrue();
    List<Patient> findByStatusFalse();
}
