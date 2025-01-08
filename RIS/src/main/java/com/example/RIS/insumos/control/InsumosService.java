package com.example.RIS.insumos.control;

import com.example.RIS.insumos.model.Insumos;
import com.example.RIS.insumos.model.InsumosRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class InsumosService {

    private final InsumosRepository insumosRepository;


    public InsumosService(InsumosRepository insumosRepository) {
        this.insumosRepository = insumosRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Insumos> insumos = insumosRepository.findAll();

        if (insumos.isEmpty()) {
            logger.info("No se encontraron proyectos");
            return new ResponseEntity<>(new Message("No se encontraron proyectos", TypesResponse.WARNING), HttpStatus.OK);
        }

        logger.info("La búsqueda ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(projects, "Lista de proyectos", TypesResponse.SUCCESS), HttpStatus.OK);
    }

}
