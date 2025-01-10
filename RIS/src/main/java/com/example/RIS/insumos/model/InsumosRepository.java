package com.example.RIS.insumos.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsumosRepository extends JpaRepository<Insumos,Long> {


    List<Insumos> findByStatus(boolean estado);

    List<Insumos> findAll();


}
