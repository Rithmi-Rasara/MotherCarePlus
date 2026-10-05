package com.nibm.mothercare.service;

import com.nibm.mothercare.entity.*;
import com.nibm.mothercare.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;
    private final ArticleRepository articleRepository;
    private final ExercisePlanRepository exercisePlanRepository;
    private final NutritionGuideRepository nutritionGuideRepository;
    private final PregnantMotherRepository pregnantMotherRepository;
    private final DoctorRepository doctorRepository;
    private final MidwifeRepository midwifeRepository;
    private final PregnancyRepository pregnancyRepository;
    private final HomeVisitRepository homeVisitRepository;
    private final NotificationRepository notificationRepository;
    private final FeedbackRepository feedbackRepository;

    public AdminService(
            UserRepository userRepository,
            AppointmentRepository appointmentRepository,
            ArticleRepository articleRepository,
            ExercisePlanRepository exercisePlanRepository,
            NutritionGuideRepository nutritionGuideRepository,
            PregnantMotherRepository pregnantMotherRepository,
            DoctorRepository doctorRepository,
            MidwifeRepository midwifeRepository,
            PregnancyRepository pregnancyRepository,
            HomeVisitRepository homeVisitRepository,
            NotificationRepository notificationRepository,
            FeedbackRepository feedbackRepository) {
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
        this.articleRepository = articleRepository;
        this.exercisePlanRepository = exercisePlanRepository;
        this.nutritionGuideRepository = nutritionGuideRepository;
        this.pregnantMotherRepository = pregnantMotherRepository;
        this.doctorRepository = doctorRepository;
        this.midwifeRepository = midwifeRepository;
        this.pregnancyRepository = pregnancyRepository;
        this.homeVisitRepository = homeVisitRepository;
        this.notificationRepository = notificationRepository;
        this.feedbackRepository = feedbackRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersByRole(String role) {
        return userRepository.findAll().stream()
                .filter(u -> role.equalsIgnoreCase(u.getRole()))
                .sorted(Comparator.comparing(User::getName, Comparator.nullsLast(String::compareToIgnoreCase)))
                .toList();
    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article createArticle(int adminId, String title, String content, String imageUrl) {
        Article a = new Article();
        a.setAdminId(adminId);
        a.setTitle(title);
        a.setContent(content);
        a.setImageUrl(imageUrl);
        a.setCreatedAt(LocalDate.now().toString());
        return articleRepository.save(a);
    }

    public void deleteArticle(int id) {
        articleRepository.deleteById(id);
    }

    public List<ExercisePlan> getAllExercisePlans() {
        return exercisePlanRepository.findAll();
    }

    public ExercisePlan createExercisePlan(int adminId, String title, String description, String imageUrl) {
        ExercisePlan ep = new ExercisePlan();
        ep.setAdminId(adminId);
        ep.setTitle(title);
        ep.setDescription(description);
        ep.setImageUrl(imageUrl);
        ep.setCreatedAt(LocalDate.now().toString());
        return exercisePlanRepository.save(ep);
    }

    public void deleteExercisePlan(int id) {
        exercisePlanRepository.deleteById(id);
    }

    public List<NutritionGuide> getAllNutritionGuides() {
        return nutritionGuideRepository.findAll();
    }

    public NutritionGuide createNutritionGuide(int adminId, String title, String description, String imageUrl) {
        NutritionGuide ng = new NutritionGuide();
        ng.setAdminId(adminId);
        ng.setTitle(title);
        ng.setDescription(description);
        ng.setImageUrl(imageUrl);
        ng.setCreatedAt(LocalDate.now().toString());
        return nutritionGuideRepository.save(ng);
    }

    public void deleteNutritionGuide(int id) {
        nutritionGuideRepository.deleteById(id);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAllByOrderByCreatedAtDesc();
    }

    public Notification sendNotification(int motherId, String title, String message, String type) {
        Notification n = new Notification();
        n.setMotherId(motherId);
        n.setTitle(title);
        n.setMessage(message);
        n.setType(type);
        n.setCreatedAt(LocalDate.now().toString());
        n.setIsRead(false);
        return notificationRepository.save(n);
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAllByOrderByCreatedAtDesc();
    }

    public Map<String, Object> getSystemReport() {
        long totalMothers = pregnantMotherRepository.count();
        long totalDoctors = doctorRepository.count();
        long totalMidwives = midwifeRepository.count();
        long totalAppointments = appointmentRepository.count();
        long highRisk = pregnancyRepository.findAll().stream()
                .filter(p -> "High Risk".equalsIgnoreCase(p.getStatus()) || "High".equalsIgnoreCase(p.getStatus()))
                .count();
        long totalVisits = homeVisitRepository.count();

        return Map.of(
                "total_mothers", totalMothers,
                "total_doctors", totalDoctors,
                "total_midwives", totalMidwives,
                "total_appointments", totalAppointments,
                "high_risk_pregnancies", highRisk,
                "total_home_visits", totalVisits
        );
    }
}