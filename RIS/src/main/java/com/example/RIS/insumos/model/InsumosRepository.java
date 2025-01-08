package com.example.RIS.insumos.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsumosRepository extends JpaRepository<Insumos, Integer> {

}
