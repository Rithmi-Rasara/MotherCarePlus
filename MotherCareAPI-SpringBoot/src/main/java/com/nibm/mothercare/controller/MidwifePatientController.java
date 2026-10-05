package com.nibm.mothercare.controller;

import com.nibm.mothercare.entity.HomeVisit;
import com.nibm.mothercare.entity.MotherAssignment;
import com.nibm.mothercare.entity.Pregnancy;
import com.nibm.mothercare.service.MidwifePatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/midwife")
public class MidwifePatientController {

    private final MidwifePatientService service;

    public MidwifePatientController(MidwifePatientService service) {
        this.service = service;
    }

    /** View Assigned Mothers with pregnancy info */
    @GetMapping("/patients")
    public ResponseEntity<List<Map<String, Object>>> getPatients(
            @RequestParam("user_id") int userId) {
        return ResponseEntity.ok(service.getAssignedMothers(userId));
    }

    /** View Pregnancy Progress for a mother */
    @GetMapping("/pregnancy")
    public ResponseEntity<?> getPregnancy(
            @RequestParam("mother_id") int motherId) {
        return service.getPregnancyByMotherId(motherId)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** Update Pregnancy Progress */
    @PutMapping("/pregnancy/{pregnancyId}")
    public ResponseEntity<Pregnancy> updatePregnancy(
            @PathVariable int pregnancyId,
            @RequestParam(required = false) Integer currentWeek,
            @RequestParam(required = false) String status) {
        return ResponseEntity.ok(service.updatePregnancyProgress(pregnancyId, currentWeek, status));
    }

    /** Record a Home Visit */
    @PostMapping("/visits")
    public ResponseEntity<HomeVisit> recordVisit(
            @RequestParam int assignmentId,
            @RequestParam(required = false) String observations,
            @RequestParam(required = false) String advice,
            @RequestParam(required = false) String pregnancyProgress) {
        return ResponseEntity.ok(service.recordHomeVisit(assignmentId, observations, advice, pregnancyProgress));
    }

    /** View visit history for an assignment */
    @GetMapping("/visits")
    public ResponseEntity<List<HomeVisit>> getVisitHistory(
            @RequestParam("assignment_id") int assignmentId) {
        return ResponseEntity.ok(service.getVisitHistory(assignmentId));
    }

    /** Assign a mother to this midwife */
    @PostMapping("/assign")
    public ResponseEntity<MotherAssignment> assignMother(
            @RequestParam int midwifeId,
            @RequestParam int motherId) {
        return ResponseEntity.ok(service.assignMother(midwifeId, motherId));
    }
}
