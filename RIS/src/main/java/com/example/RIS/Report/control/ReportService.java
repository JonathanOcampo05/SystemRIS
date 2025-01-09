package com.example.RIS.Report.control;

import com.example.RIS.Report.model.Report;
import com.example.RIS.Report.model.ReportRepository;
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
    public Report save(Report report) {
        report.setStatus(true);
        return reportRepository.save(report);
    }


    @Transactional(rollbackFor = {SQLException.class})
    public Report update(Report report) {
        Optional<Report> objectOptional = reportRepository.findById(report.getId());
        if (objectOptional.isPresent()) {
            Report objectNew = objectOptional.get();
            objectNew.setName(report.getName());
            objectNew.setDescription(report.getDescription());
            return reportRepository.save(objectNew);
        }
        return null;
    }


    public Report changeStatus(Long id) {
        Optional<Report> objectOptional = reportRepository.findById(id);
        if (objectOptional.isPresent()) {
            Report objectNew = objectOptional.get();
            objectNew.setStatus(!objectNew.isStatus());
            return reportRepository.save(objectNew);
        }
        return null;
    }

}
