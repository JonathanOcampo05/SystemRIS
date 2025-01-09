package com.example.RIS.Doctor.control;

import com.example.RIS.Doctor.model.Doctor;
import com.example.RIS.Doctor.model.DoctorDTO;
import com.example.RIS.Doctor.model.DoctorRepository;
import com.example.RIS.Doctor.model.Doctor;
import com.example.RIS.utils.Message;
import com.example.RIS.utils.TypesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Message> save(DoctorDTO doctorDTO) {
        // Validaciones del DTO
        if (doctorDTO.getName().length() > 30) {
            return new ResponseEntity<>(new Message("El nombre no puede tener más de 30 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (doctorDTO.getEmail().length() > 40) {
            return new ResponseEntity<>(new Message("El correo no puede tener más de 40 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        // Crear un nuevo Doctor a partir del DTO
        Doctor doctor = new Doctor();
        doctor.setName(doctorDTO.getName());
        doctor.setEmail(doctorDTO.getEmail());
        doctor.setStatus(true); // Por defecto se activa el estado

        // Guardar el Doctor
        doctor = doctorRepository.save(doctor);

        // Responder con el mensaje de éxito
        return new ResponseEntity<>(new Message(doctor, "El doctor se registró correctamente", TypesResponse.SUCCESS), HttpStatus.CREATED);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(DoctorDTO doctorDTO) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorDTO.getId());
        if (!doctorOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El doctor no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        Doctor doctorUpdated = doctorOptional.get();

        // Validaciones del DTO
        if (doctorDTO.getName().length() > 30) {
            return new ResponseEntity<>(new Message("El nombre no puede tener más de 30 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (doctorDTO.getEmail().length() > 40) {
            return new ResponseEntity<>(new Message("El correo no puede tener más de 40 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        // Actualizar los campos del doctor
        doctorUpdated.setName(doctorDTO.getName());
        doctorUpdated.setEmail(doctorDTO.getEmail());
        doctorUpdated.setStatus(doctorDTO.getStatus()); // Actualizar el estado si es necesario

        // Guardar el Doctor actualizado
        doctorRepository.saveAndFlush(doctorUpdated);

        // Responder con el mensaje de éxito
        return new ResponseEntity<>(new Message(doctorUpdated, "El doctor se actualizó correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }


    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> changeStatus(Long id) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if (!doctorOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El doctor no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        Doctor doctor = doctorOptional.get();
        doctor.setStatus(!doctor.isStatus());

        // Guardar el doctore con el nuevo estado
        doctorRepository.saveAndFlush(doctor);

        // Responder con el mensaje de éxito
        return new ResponseEntity<>(new Message(doctor, "El estado del doctor se cambió correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

}
