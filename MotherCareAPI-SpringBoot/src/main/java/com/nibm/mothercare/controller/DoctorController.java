package com.nibm.mothercare.controller;

import com.nibm.mothercare.entity.*;
import com.nibm.mothercare.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/{id}/patients")
    public ResponseEntity<List<Map<String, Object>>> getPatients(@PathVariable int id) {
        return ResponseEntity.ok(doctorService.getPatients(id));
    }

    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<Appointment>> getAppointments(@PathVariable int id) {
        return ResponseEntity.ok(doctorService.getAppointments(id));
    }

    @PutMapping("/{id}/appointments/{apptId}")
    public ResponseEntity<Appointment> updateAppointment(
            @PathVariable int id,
            @PathVariable int apptId,
            @RequestParam String status) {
        return ResponseEntity.ok(doctorService.updateAppointmentStatus(apptId, status));
    }

    @PostMapping("/{id}/diagnose")
    public ResponseEntity<Diagnosis> diagnose(
            @PathVariable int id,
            @RequestParam int appointmentId,
            @RequestParam String diagnosis,
            @RequestParam(required = false) String notes) {
        return ResponseEntity.ok(doctorService.diagnose(appointmentId, id, diagnosis, notes));
    }

    @PostMapping("/{id}/prescribe")
    public ResponseEntity<Prescription> prescribe(
            @PathVariable int id,
            @RequestParam int diagnosisId,
            @RequestParam String medicineName,
            @RequestParam(required = false) String dosage,
            @RequestParam(required = false) String frequency,
            @RequestParam(required = false) String duration) {
        return ResponseEntity.ok(doctorService.prescribe(diagnosisId, medicineName, dosage, frequency, duration));
    }

    @PostMapping("/{id}/reports")
    public ResponseEntity<MedicalReport> uploadReport(
            @PathVariable int id,
            @RequestParam int motherId,
            @RequestParam String reportTitle,
            @RequestParam(required = false) String reportFile) {
        return ResponseEntity.ok(doctorService.uploadMedicalReport(id, motherId, reportTitle, reportFile));
    }

    @GetMapping("/{id}/reports")
    public ResponseEntity<List<MedicalReport>> getReports(@PathVariable int id) {
        return ResponseEntity.ok(doctorService.getReportsByDoctor(id));
    }

    @GetMapping("/{id}/patients/{motherId}/health")
    public ResponseEntity<List<HealthRecord>> getPatientHealth(
            @PathVariable int id,
            @PathVariable int motherId) {
        return ResponseEntity.ok(doctorService.getPatientHealthRecords(motherId));
    }
}
