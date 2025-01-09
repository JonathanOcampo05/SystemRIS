package com.example.RIS.Doctor.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByStatusTrue();
    List<Doctor> findByStatusFalse();
}
