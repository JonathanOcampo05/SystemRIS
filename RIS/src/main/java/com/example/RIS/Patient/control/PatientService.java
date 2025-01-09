package com.example.RIS.Patient.control;

import com.example.RIS.Patient.model.Patient;
import com.example.RIS.Patient.model.PatientRepository;
import com.example.RIS.Report.model.Report;
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
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Patient save(Patient patient) {
        patient.setStatus(true);
        return patientRepository.save(patient);
    }


    @Transactional(rollbackFor = {SQLException.class})
    public Patient update(Patient patient) {
        Optional<Patient> objectOptional = patientRepository.findById(patient.getId());
        if (objectOptional.isPresent()) {
            Patient objectNew = objectOptional.get();
            objectNew.setName(patient.getName());
            objectNew.setEmail(patient.getEmail());
            objectNew.setAge(patient.getAge());
            objectNew.setTurn(patient.getTurn());
            objectNew.setSymptoms(patient.getSymptoms());
            objectNew.setSupplies(patient.getSupplies());
            return patientRepository.save(objectNew);
        }
        return null;
    }

    public Patient changeStatus(Long id) {
        Optional<Patient> objectOptional = patientRepository.findById(id);
        if (objectOptional.isPresent()) {
            Patient objectNew = objectOptional.get();
            objectNew.setStatus(!objectNew.isStatus());
            return patientRepository.save(objectNew);
        }
        return null;
    }

}
