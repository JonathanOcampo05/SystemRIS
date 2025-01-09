package com.example.RIS.Doctor.control;

import com.example.RIS.Doctor.model.Doctor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DoctorController {

    List<Doctor> doctors = new ArrayList<>();
    private long idCounter = 1;

    @GetMapping("/all")
    public List<Doctor> findAll(){
        return doctors;
    }

    @GetMapping("/{id}")
    public Doctor findById(@PathVariable Long id){
        for (Doctor d : doctors) {
            if (d.getId().equals(id)) {
                return d;
            }
        }
        return null;
    }

    @PostMapping
    public Doctor register(@RequestBody Doctor d){
        d.setId(idCounter++);
        doctors.add(d);
        return d;
    }

    @PutMapping
    public Doctor update(@RequestBody Doctor doctor){
        for (Doctor d : doctors) {
            if (d.getId().equals(doctor.getId())) {
                return d;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        for (Doctor d : doctors) {
            if (d.getId().equals(id)) {
                doctors.remove(d);
                return "El doctor fue eliminado";
            }
        }
        return "Doctor no encontrado";
    }

}
