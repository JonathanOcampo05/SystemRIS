package com.example.RIS.Patient.control;

import com.example.RIS.Patient.model.PatientDTO;
import com.example.RIS.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
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
}
