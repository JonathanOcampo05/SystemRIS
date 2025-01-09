package com.example.RIS.ManejoTurnos.control;

import com.example.RIS.ManejoTurnos.model.Turnos;
import com.example.RIS.ManejoTurnos.model.TurnosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TurnosService {
    private final TurnosRepository turnosRepository;

    @Autowired
    public TurnosService(TurnosRepository turnosRepository) {
        this.turnosRepository = turnosRepository;
    }

    @Transactional(readOnly = true)
    public List<Turnos> findAll() {
        return turnosRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Turnos> findById(Long id) {
        return turnosRepository.findById(id);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Turnos save(Turnos turnos) {
        turnos.setStatus(true);
        return turnosRepository.save(turnos);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Turnos update(Turnos turnos) {
        Optional<Turnos> turnoOptional = turnosRepository.findById(turnos.getId());
        if (turnoOptional.isPresent()) {
            Turnos turnoNew = turnoOptional.get();
            //turnoNew.setName(product.getName());
            //turnoNew.setStock(product.getStock());
            return turnosRepository.save(turnoNew);
        }
        return null;
    }

    public Turnos changeStatus(Long id) {
        Optional<Turnos> turnoOptional = turnosRepository.findById(id);
        if (turnoOptional.isPresent()) {
            Turnos turnoNew = turnoOptional.get();
            turnoNew.setStatus(!turnoNew.isStatus());
            return turnosRepository.save(turnoNew);
        }
        return null;
    }

}
