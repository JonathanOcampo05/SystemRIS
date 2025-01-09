package com.example.RIS.ManejoTurnos.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnosRepository extends JpaRepository<Turnos, Long> {
    // List<Turnos> findByStatusTrue();
    // List<Turnos> findByStatusFalse();
}
