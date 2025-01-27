package com.example.RIS.Patient.control;

import com.example.RIS.Patient.model.Patient;
import com.example.RIS.Patient.model.PatientDTO;
import com.example.RIS.utils.Message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")  // Permite solicitudes solo de este dominio
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAll() {
        return patientService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<Message> savePatient(@Validated(PatientDTO.Register.class) @RequestBody PatientDTO dto) {
        return patientService.save(dto);
    }

    @PutMapping("/update")
    public ResponseEntity<Message> updatePatient(@Validated(PatientDTO.Modify.class) @RequestBody PatientDTO dto) {
        return patientService.update(dto);
    }

    @PutMapping("/change-status")
    public ResponseEntity<Message> changeStatus(@Validated(PatientDTO.ChangeStatus.class) @RequestBody PatientDTO dto) {
        return patientService.changeStatus(dto.getId());
    }

      // Mostrar pacientes activos
    @GetMapping("/pacientesActivos")
    public String mostrarPacientesActivos(Model model) {
        List<Patient> pacientesActivos = patientService.getPacientesActivos();
        model.addAttribute("pacientesActivos", pacientesActivos);
        return "mostrarPacientes";  // Página que muestra los pacientes activos
    }

    // Cambiar estado de un paciente
    @PostMapping("/cambiarEstadoPaciente")
    public String cambiarEstadoPaciente(@RequestParam Long id, @RequestParam boolean status) {
        boolean exito = patientService.cambiarEstadoPaciente(id, status);
        return exito ? "redirect:/pacientesActivos" : "error";  // Redirige según el resultado
    }
    
}
