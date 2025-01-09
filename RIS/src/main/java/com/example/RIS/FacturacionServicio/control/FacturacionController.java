package com.example.RIS.FacturacionServicio.control;

import com.example.RIS.FacturacionServicio.model.Facturacion;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FacturacionController {

    List<Facturacion> turnos = new ArrayList<>();
    private long idCounter = 1;

    @GetMapping("/all")
    public List<Facturacion> findAll(){
        return turnos;
    }

    @GetMapping("/{id}")
    public Facturacion findById(@PathVariable Long id){
        for (Facturacion turno : turnos) {
            if (turno.getId().equals(id)) {
                return turno;
            }
        }
        return null; // Si no encuentra la materia
    }

    @PostMapping
    public Facturacion register(@RequestBody Facturacion turno){
        turno.setId(idCounter++);
        turnos.add(turno);
        return turno;
    }

    @PutMapping
    public Facturacion update(@RequestBody Facturacion turno){
        for (Facturacion t : turnos) {
            if (t.getId().equals(turno.getId())) {
                return t;
            }
        }
        return null; // Si no encuentra la materia para actualizar
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        for (Facturacion t : turnos) {
            if (t.getId().equals(id)) {
                turnos.remove(t);
                return "La materia fue eliminada";
            }
        }
        return "Materia no encontrada";
    }

}
