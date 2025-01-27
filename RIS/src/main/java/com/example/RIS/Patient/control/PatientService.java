package com.example.RIS.Patient.control;

import com.example.RIS.Patient.model.Patient;
import com.example.RIS.Patient.model.PatientDTO;
import com.example.RIS.Patient.model.PatientRepository;
import com.example.RIS.Patient.model.Patient;
import com.example.RIS.insumos.model.Insumos;
import com.example.RIS.utils.Message;
import com.example.RIS.utils.TypesResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Patient> patients = patientRepository.findAll();
        return new ResponseEntity<>(new Message(patients, "Lista de insumos", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(PatientDTO patientDTO) {
        // Validaciones del DTO
        if (patientDTO.getName().length() > 30) {
            return new ResponseEntity<>(new Message("El nombre no puede tener más de 30 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (patientDTO.getEmail().length() > 40) {
            return new ResponseEntity<>(new Message("El correo no puede tener más de 40 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        // Crear un nuevo Patient a partir del DTO
        Patient patient = new Patient();
        patient.setName(patientDTO.getName());
        patient.setEmail(patientDTO.getEmail());
        patient.setAge(patientDTO.getAge());
        patient.setTurn(patientDTO.getTurn());
        patient.setSymptoms(patientDTO.getSymptoms());
        patient.setSupplies(patientDTO.getSupplies());
        patient.setDoctor(patientDTO.getDoctor());
        patient.setStatus(true); // Por defecto se activa el estado

        // Guardar el paciente
        patient = patientRepository.save(patient);

        // Responder con el mensaje de éxito
        return new ResponseEntity<>(new Message(patient, "El paciente se registró correctamente", TypesResponse.SUCCESS), HttpStatus.CREATED);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(PatientDTO patientDTO) {
        Optional<Patient> patientOptional = patientRepository.findById(patientDTO.getId());
        if (!patientOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El paciente no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        Patient patientUpdated = patientOptional.get();

        // Validaciones del DTO
        if (patientDTO.getName().length() > 30) {
            return new ResponseEntity<>(new Message("El nombre no puede tener más de 30 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (patientDTO.getEmail().length() > 40) {
            return new ResponseEntity<>(new Message("El correo no puede tener más de 40 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        // Actualizar los campos del paciente
        patientUpdated.setName(patientDTO.getName());
        patientUpdated.setEmail(patientDTO.getEmail());
        patientUpdated.setAge(patientDTO.getAge());
        patientUpdated.setTurn(patientDTO.getTurn());
        patientUpdated.setSymptoms(patientDTO.getSymptoms());
        patientUpdated.setSupplies(patientDTO.getSupplies());
        patientUpdated.setStatus(patientDTO.getStatus()); // Actualizar el estado si es necesario

        // Guardar el paciente actualizado
        patientRepository.saveAndFlush(patientUpdated);

        // Responder con el mensaje de éxito
        return new ResponseEntity<>(new Message(patientUpdated, "El paciente se actualizó correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }


    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> changeStatus(Long id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (!patientOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El paciente no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        Patient patient = patientOptional.get();
        patient.setStatus(!patient.isStatus());

        // Guardar el patiente con el nuevo estado
        patientRepository.saveAndFlush(patient);

        // Responder con el mensaje de éxito
        return new ResponseEntity<>(new Message(patient, "El estado del paciente se cambió correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

     // Método para obtener pacientes activos
     public List<Patient> getPacientesActivos() {
        return patientRepository.findByStatusTrue();
    }

    // Método para cambiar el estado de un paciente
    public boolean cambiarEstadoPaciente(Long id, boolean nuevoEstado) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient != null) {
            patient.setStatus(nuevoEstado);
            patientRepository.save(patient);
            return true;
        }
        return false;
    }

    
}
