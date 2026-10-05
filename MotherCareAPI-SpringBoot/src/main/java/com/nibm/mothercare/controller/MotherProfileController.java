package com.nibm.mothercare.controller;

import com.nibm.mothercare.entity.*;
import com.nibm.mothercare.service.MotherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for Pregnant Mother use-cases.
 * All endpoints under /api/mothers
 */
@RestController
@RequestMapping("/api/mothers")
public class MotherProfileController {

    private final MotherService motherService;

    public MotherProfileController(MotherService motherService) {
        this.motherService = motherService;
    }

    // GET /api/mothers/{id}/profile
    @GetMapping("/{id}/profile")
    public ResponseEntity<?> getProfile(@PathVariable int id) {
        return motherService.getProfile(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /api/mothers/{id}/profile
    @PutMapping("/{id}/profile")
    public ResponseEntity<PregnantMother> updateProfile(
            @PathVariable int id,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String address) {
        return ResponseEntity.ok(motherService.updateProfile(id, phone, address));
    }

    // GET /api/mothers/{id}/pregnancy
    @GetMapping("/{id}/pregnancy")
    public ResponseEntity<?> getPregnancy(@PathVariable int id) {
        return motherService.getPregnancy(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/mothers/{id}/pregnancy
    @PostMapping("/{id}/pregnancy")
    public ResponseEntity<Pregnancy> startPregnancy(
            @PathVariable int id,
            @RequestParam String startDate,
            @RequestParam String dueDate) {
        return ResponseEntity.ok(motherService.startPregnancy(id, startDate, dueDate));
    }

    // GET /api/mothers/{pregnancyId}/health-records
    @GetMapping("/{pregnancyId}/health-records")
    public ResponseEntity<List<HealthRecord>> getHealthRecords(@PathVariable int pregnancyId) {
        return ResponseEntity.ok(motherService.getHealthRecords(pregnancyId));
    }

    // POST /api/mothers/{pregnancyId}/health-records
    @PostMapping("/{pregnancyId}/health-records")
    public ResponseEntity<HealthRecord> addHealthRecord(
            @PathVariable int pregnancyId,
            @RequestParam(required = false) Double weight,
            @RequestParam(required = false) String bloodPressure,
            @RequestParam(required = false) Double bloodSugar,
            @RequestParam(required = false) Double waterIntake,
            @RequestParam(required = false) String notes) {
        return ResponseEntity.ok(motherService.addHealthRecord(pregnancyId, weight, bloodPressure, bloodSugar, waterIntake, notes));
    }

    // GET /api/mothers/{pregnancyId}/baby-growth
    @GetMapping("/{pregnancyId}/baby-growth")
    public ResponseEntity<List<BabyGrowth>> getBabyGrowth(@PathVariable int pregnancyId) {
        return ResponseEntity.ok(motherService.getBabyGrowth(pregnancyId));
    }

    // POST /api/mothers/{pregnancyId}/baby-growth
    @PostMapping("/{pregnancyId}/baby-growth")
    public ResponseEntity<BabyGrowth> addBabyGrowth(
            @PathVariable int pregnancyId,
            @RequestParam int week,
            @RequestParam(required = false) Double weight,
            @RequestParam(required = false) Double length,
            @RequestParam(required = false) String notes) {
        return ResponseEntity.ok(motherService.addBabyGrowth(pregnancyId, week, weight, length, notes));
    }

    // GET /api/mothers/{id}/appointments
    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<Appointment>> getAppointments(@PathVariable int id) {
        return ResponseEntity.ok(motherService.getAppointments(id));
    }

    // POST /api/mothers/{id}/appointments
    @PostMapping("/{id}/appointments")
    public ResponseEntity<Appointment> bookAppointment(
            @PathVariable int id,
            @RequestParam int doctorId,
            @RequestParam String appointmentDate,
            @RequestParam(required = false) String appointmentTime,
            @RequestParam(required = false) String reason) {
        return ResponseEntity.ok(motherService.bookAppointment(id, doctorId, appointmentDate, appointmentTime, reason));
    }

    // GET /api/mothers/{id}/reminders
    @GetMapping("/{id}/reminders")
    public ResponseEntity<List<Reminder>> getReminders(@PathVariable int id) {
        return ResponseEntity.ok(motherService.getReminders(id));
    }

    // GET /api/mothers/{id}/vaccinations
    @GetMapping("/{id}/vaccinations")
    public ResponseEntity<List<Vaccination>> getVaccinations(@PathVariable int id) {
        return ResponseEntity.ok(motherService.getVaccinations(id));
    }

    // GET /api/mothers/{id}/medical-reports
    @GetMapping("/{id}/medical-reports")
    public ResponseEntity<List<MedicalReport>> getMedicalReports(@PathVariable int id) {
        return ResponseEntity.ok(motherService.getMedicalReports(id));
    }

    // GET /api/mothers/nutrition
    @GetMapping("/nutrition")
    public ResponseEntity<List<NutritionGuide>> getNutritionGuides() {
        return ResponseEntity.ok(motherService.getNutritionGuides());
    }

    // GET /api/mothers/exercise
    @GetMapping("/exercise")
    public ResponseEntity<List<ExercisePlan>> getExercisePlans() {
        return ResponseEntity.ok(motherService.getExercisePlans());
    }
}
