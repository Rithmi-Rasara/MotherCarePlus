package com.nibm.mothercare.controller;

import com.nibm.mothercare.entity.*;
import com.nibm.mothercare.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Users
    @GetMapping("/users")
    public ResponseEntity<?> getUsers(@RequestParam(required = false) String role) {
        if (role != null && !role.isBlank()) {
            return ResponseEntity.ok(adminService.getUsersByRole(role));
        }
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        adminService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Appointments
    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(adminService.getAllAppointments());
    }

    // Articles
    @GetMapping("/articles")
    public ResponseEntity<List<Article>> getArticles() {
        return ResponseEntity.ok(adminService.getAllArticles());
    }

    @PostMapping("/articles")
    public ResponseEntity<Article> createArticle(
            @RequestParam int adminId,
            @RequestParam String title,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) String imageUrl) {
        return ResponseEntity.ok(adminService.createArticle(adminId, title, content, imageUrl));
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable int id) {
        adminService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }

    // Exercise Plans
    @GetMapping("/exercise")
    public ResponseEntity<List<ExercisePlan>> getExercisePlans() {
        return ResponseEntity.ok(adminService.getAllExercisePlans());
    }

    @PostMapping("/exercise")
    public ResponseEntity<ExercisePlan> createExercisePlan(
            @RequestParam int adminId,
            @RequestParam String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String imageUrl) {
        return ResponseEntity.ok(adminService.createExercisePlan(adminId, title, description, imageUrl));
    }

    @DeleteMapping("/exercise/{id}")
    public ResponseEntity<Void> deleteExercisePlan(@PathVariable int id) {
        adminService.deleteExercisePlan(id);
        return ResponseEntity.noContent().build();
    }

    // Nutrition Guides
    @GetMapping("/nutrition")
    public ResponseEntity<List<NutritionGuide>> getNutritionGuides() {
        return ResponseEntity.ok(adminService.getAllNutritionGuides());
    }

    @PostMapping("/nutrition")
    public ResponseEntity<NutritionGuide> createNutritionGuide(
            @RequestParam int adminId,
            @RequestParam String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String imageUrl) {
        return ResponseEntity.ok(adminService.createNutritionGuide(adminId, title, description, imageUrl));
    }

    @DeleteMapping("/nutrition/{id}")
    public ResponseEntity<Void> deleteNutritionGuide(@PathVariable int id) {
        adminService.deleteNutritionGuide(id);
        return ResponseEntity.noContent().build();
    }

    // Notifications
    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getNotifications() {
        return ResponseEntity.ok(adminService.getAllNotifications());
    }

    @PostMapping("/notifications")
    public ResponseEntity<Notification> sendNotification(
            @RequestParam int motherId,
            @RequestParam String title,
            @RequestParam String message,
            @RequestParam(required = false, defaultValue = "General") String type) {
        return ResponseEntity.ok(adminService.sendNotification(motherId, title, message, type));
    }

    // Feedback
    @GetMapping("/feedback")
    public ResponseEntity<List<Feedback>> getFeedback() {
        return ResponseEntity.ok(adminService.getAllFeedback());
    }

    // System Reports
    @GetMapping("/reports")
    public ResponseEntity<Map<String, Object>> getSystemReport() {
        return ResponseEntity.ok(adminService.getSystemReport());
    }
}
