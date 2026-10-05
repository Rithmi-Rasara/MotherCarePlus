package com.nibm.mothercare.service;

import com.nibm.mothercare.entity.*;
import com.nibm.mothercare.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final MedicalReportRepository medicalReportRepository;
    private final PregnantMotherRepository pregnantMotherRepository;
    private final PregnancyRepository pregnancyRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final HealthRecordRepository healthRecordRepository;

    public DoctorService(
            DoctorRepository doctorRepository,
            AppointmentRepository appointmentRepository,
            MedicalReportRepository medicalReportRepository,
            PregnantMotherRepository pregnantMotherRepository,
            PregnancyRepository pregnancyRepository,
            DiagnosisRepository diagnosisRepository,
            PrescriptionRepository prescriptionRepository,
            HealthRecordRepository healthRecordRepository) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
        this.medicalReportRepository = medicalReportRepository;
        this.pregnantMotherRepository = pregnantMotherRepository;
        this.pregnancyRepository = pregnancyRepository;
        this.diagnosisRepository = diagnosisRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.healthRecordRepository = healthRecordRepository;
    }

    // View patients assigned (those who booked appointments with this doctor)
    public List<Map<String, Object>> getPatients(int doctorId) {
        List<Appointment> appointments = appointmentRepository.findByDoctorId(doctorId);
        List<Integer> motherIds = appointments.stream()
                .map(Appointment::getMotherId)
                .distinct()
                .collect(Collectors.toList());

        if (motherIds.isEmpty()) {
            return Collections.emptyList();
        }

        List<PregnantMother> mothers = pregnantMotherRepository.findAllById(motherIds);
        mothers.sort((m1, m2) -> {
            String name1 = m1.getName() != null ? m1.getName() : "";
            String name2 = m2.getName() != null ? m2.getName() : "";
            return name1.compareToIgnoreCase(name2);
        });

        List<Map<String, Object>> result = new ArrayList<>();
        for (PregnantMother mother : mothers) {
            Map<String, Object> map = new HashMap<>();
            map.put("mother_id", mother.getMotherId());
            map.put("name", mother.getName());
            map.put("email", mother.getEmail());
            map.put("phone", mother.getPhone());

            Optional<Pregnancy> pregnancyOpt = pregnancyRepository
                    .findFirstByMotherIdOrderByPregnancyIdDesc(mother.getMotherId());

            if (pregnancyOpt.isPresent()) {
                Pregnancy p = pregnancyOpt.get();
                map.put("pregnancy_id", p.getPregnancyId());
                map.put("start_date", p.getStartDate());
                map.put("due_date", p.getDueDate());
                map.put("current_week", p.getCurrentWeek());
                map.put("pregnancy_status", p.getStatus());
            } else {
                map.put("pregnancy_id", null);
                map.put("start_date", null);
                map.put("due_date", null);
                map.put("current_week", null);
                map.put("pregnancy_status", null);
            }

            result.add(map);
        }
        return result;
    }

    // Get doctor's appointments
    public List<Appointment> getAppointments(int doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    // Approve or Reject an appointment
    public Appointment updateAppointmentStatus(int appointmentId, String status) {
        Appointment a = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found: " + appointmentId));
        a.setStatus(status);
        return appointmentRepository.save(a);
    }

    // Diagnose: create a Diagnosis record via JPA Repository
    public Diagnosis diagnose(int appointmentId, int doctorId, String diagnosisText, String notes) {
        Diagnosis d = new Diagnosis();
        d.setAppointmentId(appointmentId);
        d.setDoctorId(doctorId);
        d.setDiagnosis(diagnosisText);
        d.setNotes(notes);
        d.setDiagnosisDate(LocalDate.now().toString());
        return diagnosisRepository.save(d);
    }

    // Prescribe medicines via JPA Repository
    public Prescription prescribe(int diagnosisId, String medicineName, String dosage,
                                   String frequency, String duration) {
        Prescription p = new Prescription();
        p.setDiagnosisId(diagnosisId);
        p.setMedicineName(medicineName);
        p.setDosage(dosage);
        p.setFrequency(frequency);
        p.setDuration(duration);
        return prescriptionRepository.save(p);
    }

    // Upload / create medical report
    public MedicalReport uploadMedicalReport(int doctorId, int motherId,
                                              String reportTitle, String reportFile) {
        MedicalReport r = new MedicalReport();
        r.setDoctorId(doctorId);
        r.setMotherId(motherId);
        r.setReportTitle(reportTitle);
        r.setReportFile(reportFile);
        r.setUploadDate(LocalDate.now().toString());
        return medicalReportRepository.save(r);
    }

    // View medical reports uploaded by this doctor
    public List<MedicalReport> getReportsByDoctor(int doctorId) {
        return medicalReportRepository.findByDoctorId(doctorId);
    }

    // Monitor patient health records via pregnancy using JPA
    public List<HealthRecord> getPatientHealthRecords(int motherId) {
        Optional<Pregnancy> pregnancyOpt = pregnancyRepository.findFirstByMotherIdOrderByPregnancyIdDesc(motherId);
        if (pregnancyOpt.isPresent()) {
            return healthRecordRepository.findByPregnancyId(pregnancyOpt.get().getPregnancyId());
        }
        return Collections.emptyList();
    }
}
