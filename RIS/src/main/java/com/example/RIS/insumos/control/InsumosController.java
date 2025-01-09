package com.example.RIS.insumos.control;

import com.example.RIS.insumos.model.InsumosDTO;

import com.example.RIS.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/insumos")
public class InsumosController {

    private final InsumosService insumosService;

    @Autowired
    public InsumosController(InsumosService insumosService) {
        this.insumosService = insumosService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAll() {
        return insumosService.findAll();
    }

    @GetMapping("/active")
    public ResponseEntity<Message> getActive() {
        return insumosService.findActive();
    }

    @PostMapping("/save")
    public ResponseEntity<Message> saveProjects(@Validated(InsumosDTO.Register.class) @RequestBody InsumosDTO dto) {
        return insumosService.save(dto);
    }

    @PutMapping("/update")
    public ResponseEntity<Message> updateProjects(@Validated(InsumosDTO.Modify.class) @RequestBody InsumosDTO dto) {
        return insumosService.update(dto);
    }

    @PutMapping("/change-status")
    public ResponseEntity<Message> changeStatus(@Validated(InsumosDTO.ChangeStatus.class) @RequestBody InsumosDTO dto) {
        return insumosService.changeStatus(dto);
    }


}
