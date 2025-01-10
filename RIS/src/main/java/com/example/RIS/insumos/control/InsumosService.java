package com.example.RIS.insumos.control;

import com.example.RIS.insumos.model.Insumos;
import com.example.RIS.insumos.model.InsumosDTO;
import com.example.RIS.insumos.model.InsumosRepository;
import com.example.RIS.utils.Message;
import com.example.RIS.utils.TypesResponse;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
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
            //logger.info("No se encontraron proyectos");
            return new ResponseEntity<>(new Message("No se encontraron insumos", TypesResponse.WARNING), HttpStatus.OK);
        }

        //logger.info("La búsqueda ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(insumos, "Lista de insumos", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    // Encontrar todos los activos
    @Transactional(readOnly = true)
    public ResponseEntity<Message> findActive() {
        List<Insumos> activeInsumos = insumosRepository.findByStatus(true);

        if (activeInsumos.isEmpty()) {
            //logger.info("No se encontraron proyectos activos");
            return new ResponseEntity<>(new Message("No se encontraron insumos activos", TypesResponse.WARNING), HttpStatus.OK);
        }

        //logger.info("Búsqueda de proyectos activos realizada correctamente");
        return new ResponseEntity<>(new Message(activeInsumos, "Lista de insumos activos", TypesResponse.SUCCESS), HttpStatus.OK);
    }


    // Guardar proyecto
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(InsumosDTO dto) {
        try {


            // Crear un nuevo proyecto
            Insumos newInsumo = new Insumos(
                    dto.getNombre(),
                    dto.getTipo(),
                    dto.getDescripcion(),
                    dto.getCantidad(),
                    true);

            newInsumo = insumosRepository.saveAndFlush(newInsumo);

            return new ResponseEntity<>(new Message(newInsumo, "Insumo guardado exitosamente", TypesResponse.SUCCESS), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new Message(e.getMessage(), TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message("Revise los datos e inténtelo de nuevo", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(InsumosDTO dto) {

        Insumos insumos = insumosRepository.findById(dto.getId_insumo())
                .orElseThrow(() -> new RuntimeException("El proyecto no existe"));




        insumos.setNombre(dto.getNombre());
        insumos.setDescripcion(dto.getDescripcion());
        insumos.setTipo(dto.getTipo());
        insumos.setCantidad(dto.getCantidad());

        try {
            insumos = insumosRepository.saveAndFlush(insumos);
            return new ResponseEntity<>(new Message(insumos, "Insumo actualizado exitosamente", TypesResponse.SUCCESS), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message("Revise los datos e inténtelo de nuevo", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
    }




    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> changeStatus(InsumosDTO dto) {
        try {

            Insumos insumo = insumosRepository.findById(dto.getId_insumo())
                    .orElseThrow(() -> new RuntimeException("El insumo no existe"));

            insumo.setStatus(!insumo.isStatus());

            insumo = insumosRepository.saveAndFlush(insumo);

            return new ResponseEntity<>(new Message(insumo, "El estado del insumo ha sido actualizado exitosamente", TypesResponse.SUCCESS), HttpStatus.OK);

        } catch (RuntimeException e) {

            return new ResponseEntity<>(new Message(e.getMessage(), TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {

            return new ResponseEntity<>(new Message("Hubo un problema al cambiar el estado del insumo, intente nuevamente.", TypesResponse.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
