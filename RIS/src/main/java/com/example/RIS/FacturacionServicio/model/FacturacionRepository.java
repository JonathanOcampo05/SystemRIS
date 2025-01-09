package com.example.RIS.FacturacionServicio.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturacionRepository extends JpaRepository<Facturacion, Long> {
    // List<Turnos> findByStatusTrue();
    // List<Turnos> findByStatusFalse();
}
