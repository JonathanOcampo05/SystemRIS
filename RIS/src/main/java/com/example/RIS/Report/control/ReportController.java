package com.example.RIS.Report.control;

import com.example.RIS.Patient.model.Patient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    List<Patient> patient = new ArrayList<>();
    private long idCounter = 1;

    @GetMapping("/all")
    public List<Patient> findAll(){
        return patient;
    }

    @GetMapping("/{id}")
    public Patient findById(@PathVariable Long id){
        for (Patient p : patient) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null; // Si no encuentra la materia
    }

    @PostMapping
    public Patient register(@RequestBody Patient turno){
        turno.setId(idCounter++);
        patient.add(turno);
        return turno;
    }

    @PutMapping
    public Patient update(@RequestBody Patient turno){
        for (Patient p : patient) {
            if (p.getId().equals(turno.getId())) {
                return p;
            }
        }
        return null; // Si no encuentra la materia para actualizar
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        for (Patient p : patient) {
            if (p.getId().equals(id)) {
                patient.remove(p);
                return "El paciente fue eliminado";
            }
        }
        return "Paciente no encontrado";
    }

}
