package com.nibm.mothercare.service;

import com.nibm.mothercare.entity.*;
import com.nibm.mothercare.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MotherService {

    private final PregnantMotherRepository motherRepository;
    private final PregnancyRepository pregnancyRepository;
    private final HealthRecordRepository healthRecordRepository;
    private final BabyGrowthRepository babyGrowthRepository;
    private final AppointmentRepository appointmentRepository;
    private final MedicalReportRepository medicalReportRepository;
    private final NutritionGuideRepository nutritionGuideRepository;
    private final ExercisePlanRepository exercisePlanRepository;
    private final ReminderRepository reminderRepository;
    private final VaccinationRepository vaccinationRepository;

    public MotherService(
            PregnantMotherRepository motherRepository,
            PregnancyRepository pregnancyRepository,
            HealthRecordRepository healthRecordRepository,
            BabyGrowthRepository babyGrowthRepository,
            AppointmentRepository appointmentRepository,
            MedicalReportRepository medicalReportRepository,
            NutritionGuideRepository nutritionGuideRepository,
            ExercisePlanRepository exercisePlanRepository,
            ReminderRepository reminderRepository,
            VaccinationRepository vaccinationRepository) {
        this.motherRepository = motherRepository;
        this.pregnancyRepository = pregnancyRepository;
        this.healthRecordRepository = healthRecordRepository;
        this.babyGrowthRepository = babyGrowthRepository;
        this.appointmentRepository = appointmentRepository;
        this.medicalReportRepository = medicalReportRepository;
        this.nutritionGuideRepository = nutritionGuideRepository;
        this.exercisePlanRepository = exercisePlanRepository;
        this.reminderRepository = reminderRepository;
        this.vaccinationRepository = vaccinationRepository;
    }

    // Profile
    public Optional<PregnantMother> getProfile(int motherId) {
        return motherRepository.findById(motherId);
    }

    public PregnantMother updateProfile(int motherId, String phone, String address) {
        PregnantMother m = motherRepository.findById(motherId)
                .orElseThrow(() -> new RuntimeException("Mother not found: " + motherId));
        if (phone != null && !phone.isBlank()) m.setPhone(phone);
        if (address != null && !address.isBlank()) m.setAddress(address);
        return motherRepository.save(m);
    }

    // Pregnancy
    public Optional<Pregnancy> getPregnancy(int motherId) {
        return pregnancyRepository.findFirstByMotherIdOrderByPregnancyIdDesc(motherId);
    }

    public Pregnancy startPregnancy(int motherId, String startDate, String dueDate) {
        Pregnancy p = new Pregnancy();
        p.setMotherId(motherId);
        p.setStartDate(startDate);
        p.setDueDate(dueDate);
        p.setCurrentWeek(1);
        p.setStatus("Normal");
        return pregnancyRepository.save(p);
    }

    // Health Records
    public List<HealthRecord> getHealthRecords(int pregnancyId) {
        return healthRecordRepository.findByPregnancyId(pregnancyId);
    }

    public HealthRecord addHealthRecord(int pregnancyId, Double weight, String bloodPressure,
                                         Double bloodSugar, Double waterIntake, String notes) {
        HealthRecord hr = new HealthRecord();
        hr.setPregnancyId(pregnancyId);
        hr.setRecordDate(LocalDate.now().toString());
        hr.setWeight(weight);
        hr.setBloodPressure(bloodPressure);
        hr.setBloodSugar(bloodSugar);
        hr.setWaterIntake(waterIntake);
        hr.setNotes(notes);
        return healthRecordRepository.save(hr);
    }

    // Baby Growth
    public List<BabyGrowth> getBabyGrowth(int pregnancyId) {
        return babyGrowthRepository.findByPregnancyId(pregnancyId);
    }

    public BabyGrowth addBabyGrowth(int pregnancyId, int week, Double weight, Double length, String notes) {
        BabyGrowth bg = new BabyGrowth();
        bg.setPregnancyId(pregnancyId);
        bg.setWeek(week);
        bg.setWeight(weight);
        bg.setLength(length);
        bg.setNotes(notes);
        return babyGrowthRepository.save(bg);
    }

    // Appointments
    public List<Appointment> getAppointments(int motherId) {
        return appointmentRepository.findByMotherId(motherId);
    }

    public Appointment bookAppointment(int motherId, int doctorId, String appointmentDate,
                                        String appointmentTime, String reason) {
        Appointment a = new Appointment();
        a.setMotherId(motherId);
        a.setDoctorId(doctorId);
        a.setAppointmentDate(appointmentDate);
        a.setAppointmentTime(appointmentTime);
        a.setReason(reason);
        a.setStatus("Pending");
        return appointmentRepository.save(a);
    }

    // Medical Reports
    public List<MedicalReport> getMedicalReports(int motherId) {
        return medicalReportRepository.findByMotherId(motherId);
    }

    // Reminders (medicine + vaccination) via Spring Data JPA
    public List<Reminder> getReminders(int motherId) {
        return reminderRepository.findByMotherIdOrderByReminderDateAscReminderTimeAsc(motherId);
    }

    // Vaccinations via Spring Data JPA
    public List<Vaccination> getVaccinations(int motherId) {
        return vaccinationRepository.findByMotherIdOrderByVaccinationDateAsc(motherId);
    }

    // Nutrition Guides
    public List<NutritionGuide> getNutritionGuides() {
        return nutritionGuideRepository.findAll();
    }

    // Exercise Plans
    public List<ExercisePlan> getExercisePlans() {
        return exercisePlanRepository.findAll();
    }
}
