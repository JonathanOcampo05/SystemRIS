package com.example.RIS.ManejoTurnos.control;

import com.example.RIS.ManejoTurnos.model.Turnos;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TurnosController {

    List<Turnos> turnos = new ArrayList<>();
    private long idCounter = 1;

    @GetMapping("/all")
    public List<Turnos> findAll(){
        return turnos;
    }

    @GetMapping("/{id}")
    public Turnos findById(@PathVariable Long id){
        for (Turnos turno : turnos) {
            if (turno.getId().equals(id)) {
                return turno;
            }
        }
        return null; // Si no encuentra la materia
    }

    @PostMapping
    public Turnos register(@RequestBody Turnos turno){
        turno.setId(idCounter++);
        turnos.add(turno);
        return turno;
    }

    @PutMapping
    public Turnos update(@RequestBody Turnos turno){
        for (Turnos t : turnos) {
            if (t.getId().equals(turno.getId())) {
                return t;
            }
        }
        return null; // Si no encuentra la materia para actualizar
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        for (Turnos t : turnos) {
            if (t.getId().equals(id)) {
                turnos.remove(t);
                return "La materia fue eliminada";
            }
        }
        return "Materia no encontrada";
    }

}
