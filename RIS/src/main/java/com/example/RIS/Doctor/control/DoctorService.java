package com.example.RIS.Doctor.control;

import com.example.RIS.Doctor.model.Doctor;
import com.example.RIS.Doctor.model.DoctorRepository;
import com.example.RIS.Doctor.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Transactional(readOnly = true)
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Doctor save(Doctor doctor) {
        doctor.setStatus(true);
        return doctorRepository.save(doctor);
    }


    @Transactional(rollbackFor = {SQLException.class})
    public Doctor update(Doctor doctor) {
        Optional<Doctor> objectOptional = doctorRepository.findById(doctor.getId());
        if (objectOptional.isPresent()) {
            Doctor objectNew = objectOptional.get();
            objectNew.setName(doctor.getName());
            objectNew.setEmail(doctor.getEmail());
            return doctorRepository.save(objectNew);
        }
        return null;
    }

    public Doctor changeStatus(Long id) {
        Optional<Doctor> objectOptional = doctorRepository.findById(id);
        if (objectOptional.isPresent()) {
            Doctor objectNew = objectOptional.get();
            objectNew.setStatus(!objectNew.isStatus());
            return doctorRepository.save(objectNew);
        }
        return null;
    }

}
