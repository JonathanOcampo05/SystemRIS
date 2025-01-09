package com.example.RIS.Report.control;

import com.example.RIS.Report.model.Report;
import com.example.RIS.Report.model.ReportDTO;
import com.example.RIS.Report.model.ReportRepository;
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
public class ReportService {
    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Transactional(readOnly = true)
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Report> findById(Long id) {
        return reportRepository.findById(id);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(ReportDTO reportDTO) {
        // Validaciones del DTO
        if (reportDTO.getName().length() > 30) {
            return new ResponseEntity<>(new Message("El nombre no puede tener más de 30 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (reportDTO.getDescription().length() > 100) {
            return new ResponseEntity<>(new Message("La descripción no puede tener más de 100 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        // Crear un nuevo Report a partir del DTO
        Report report = new Report();
        report.setName(reportDTO.getName());
        report.setDescription(reportDTO.getDescription());
        report.setStatus(true); // Por defecto se activa el estado

        // Guardar el reporte
        report = reportRepository.save(report);

        // Responder con el mensaje de éxito
        return new ResponseEntity<>(new Message(report, "El reporte se registró correctamente", TypesResponse.SUCCESS), HttpStatus.CREATED);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(ReportDTO reportDTO) {
        Optional<Report> reportOptional = reportRepository.findById(reportDTO.getId());
        if (!reportOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El reporte no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        Report reportUpdated = reportOptional.get();

        // Validaciones del DTO
        if (reportDTO.getName().length() > 30) {
            return new ResponseEntity<>(new Message("El nombre no puede tener más de 30 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (reportDTO.getDescription().length() > 100) {
            return new ResponseEntity<>(new Message("La descripción no puede tener más de 100 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        // Actualizar los campos del reporte
        reportUpdated.setName(reportDTO.getName());
        reportUpdated.setDescription(reportDTO.getDescription());
        reportUpdated.setStatus(reportDTO.getStatus()); // Actualizar el estado si es necesario

        // Guardar el reporte actualizado
        reportRepository.saveAndFlush(reportUpdated);

        // Responder con el mensaje de éxito
        return new ResponseEntity<>(new Message(reportUpdated, "El reporte se actualizó correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> changeStatus(Long id) {
        Optional<Report> reportOptional = reportRepository.findById(id);
        if (!reportOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El reporte no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        Report report = reportOptional.get();
        report.setStatus(!report.isStatus());

        // Guardar el reporte con el nuevo estado
        reportRepository.saveAndFlush(report);

        // Responder con el mensaje de éxito
        return new ResponseEntity<>(new Message(report, "El estado del reporte se cambió correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }
}
