package com.example.RIS.FacturacionServicio.control;

import com.example.RIS.FacturacionServicio.model.Facturacion;
import com.example.RIS.FacturacionServicio.model.FacturacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class FacturacionService {
    private final FacturacionRepository facturacionRepository;

    @Autowired
    public FacturacionService(FacturacionRepository facturacionRepository) {
        this.facturacionRepository = facturacionRepository;
    }

    @Transactional(readOnly = true)
    public List<Facturacion> findAll() {
        return facturacionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Facturacion> findById(Long id) {
        return facturacionRepository.findById(id);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Facturacion save(Facturacion facturacion) {
        facturacion.setStatus(true);
        return facturacionRepository.save(facturacion);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Facturacion update(Facturacion facturacion) {
        Optional<Facturacion> turnoOptional = facturacionRepository.findById(facturacion.getId());
        if (turnoOptional.isPresent()) {
            Facturacion turnoNew = turnoOptional.get();
            //turnoNew.setName(product.getName());
            //turnoNew.setStock(product.getStock());
            return facturacionRepository.save(turnoNew);
        }
        return null;
    }

    public Facturacion changeStatus(Long id) {
        Optional<Facturacion> turnoOptional = facturacionRepository.findById(id);
        if (turnoOptional.isPresent()) {
            Facturacion turnoNew = turnoOptional.get();
            turnoNew.setStatus(!turnoNew.isStatus());
            return facturacionRepository.save(turnoNew);
        }
        return null;
    }

}
