package com.nibm.mothercare.service;

import com.nibm.mothercare.entity.HomeVisit;
import com.nibm.mothercare.entity.MotherAssignment;
import com.nibm.mothercare.entity.Pregnancy;
import com.nibm.mothercare.entity.PregnantMother;
import com.nibm.mothercare.repository.HomeVisitRepository;
import com.nibm.mothercare.repository.MidwifeProfileRepository;
import com.nibm.mothercare.repository.MotherAssignmentRepository;
import com.nibm.mothercare.repository.PregnancyRepository;
import com.nibm.mothercare.repository.PregnantMotherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class MidwifePatientService {

    private final MidwifeProfileRepository midwifeProfileRepository;
    private final MotherAssignmentRepository motherAssignmentRepository;
    private final PregnancyRepository pregnancyRepository;
    private final HomeVisitRepository homeVisitRepository;
    private final PregnantMotherRepository pregnantMotherRepository;

    public MidwifePatientService(
            MidwifeProfileRepository midwifeProfileRepository,
            MotherAssignmentRepository motherAssignmentRepository,
            PregnancyRepository pregnancyRepository,
            HomeVisitRepository homeVisitRepository,
            PregnantMotherRepository pregnantMotherRepository) {
        this.midwifeProfileRepository = midwifeProfileRepository;
        this.motherAssignmentRepository = motherAssignmentRepository;
        this.pregnancyRepository = pregnancyRepository;
        this.homeVisitRepository = homeVisitRepository;
        this.pregnantMotherRepository = pregnantMotherRepository;
    }

    public int resolveMidwifeId(int userId) {
        return midwifeProfileRepository.findByUserId(userId)
                .map(p -> p.getMidwifeId())
                .orElse(userId);
    }

    // View Assigned Mothers with pregnancy info using Spring Data JPA
    public List<Map<String, Object>> getAssignedMothers(int userId) {
        int midwifeId = resolveMidwifeId(userId);
        List<MotherAssignment> assignments = motherAssignmentRepository.findByMidwifeId(midwifeId);
        
        List<Map<String, Object>> result = new ArrayList<>();
        for (MotherAssignment ma : assignments) {
            Optional<PregnantMother> motherOpt = pregnantMotherRepository.findById(ma.getMotherId());
            if (motherOpt.isPresent()) {
                PregnantMother pm = motherOpt.get();
                Map<String, Object> map = new HashMap<>();
                map.put("mother_id", pm.getMotherId());
                map.put("name", pm.getName());
                map.put("email", pm.getEmail());
                map.put("phone", pm.getPhone());
                map.put("address", pm.getAddress());
                map.put("assignment_id", ma.getAssignmentId());
                map.put("assigned_date", ma.getAssignedDate());
                map.put("assignment_status", ma.getStatus());

                pregnancyRepository.findFirstByMotherIdOrderByPregnancyIdDesc(pm.getMotherId())
                        .ifPresent(p -> {
                            map.put("pregnancy_id", p.getPregnancyId());
                            map.put("start_date", p.getStartDate());
                            map.put("due_date", p.getDueDate());
                            map.put("current_week", p.getCurrentWeek());
                            map.put("pregnancy_status", p.getStatus());
                        });

                result.add(map);
            }
        }
        return result;
    }

    public Optional<Pregnancy> getPregnancyByMotherId(int motherId) {
        return pregnancyRepository.findFirstByMotherIdOrderByPregnancyIdDesc(motherId);
    }

    public Pregnancy updatePregnancyProgress(int pregnancyId, Integer currentWeek, String status) {
        Pregnancy pregnancy = pregnancyRepository.findById(pregnancyId)
                .orElseThrow(() -> new RuntimeException("Pregnancy record not found: " + pregnancyId));
        if (currentWeek != null) pregnancy.setCurrentWeek(currentWeek);
        if (status != null && !status.isBlank()) pregnancy.setStatus(status);
        return pregnancyRepository.save(pregnancy);
    }

    public HomeVisit recordHomeVisit(int assignmentId, String observations, String advice, String pregnancyProgress) {
        HomeVisit visit = new HomeVisit();
        visit.setAssignmentId(assignmentId);
        visit.setVisitDate(LocalDate.now().toString());
        visit.setObservations(observations);
        visit.setAdvice(advice);
        visit.setPregnancyProgress(pregnancyProgress);
        return homeVisitRepository.save(visit);
    }

    // View Home Visit history for an assignment via JPA
    public List<HomeVisit> getVisitHistory(int assignmentId) {
        return homeVisitRepository.findByAssignmentId(assignmentId);
    }

    public MotherAssignment assignMother(int midwifeId, int motherId) {
        MotherAssignment ma = new MotherAssignment();
        ma.setMidwifeId(midwifeId);
        ma.setMotherId(motherId);
        ma.setAssignedDate(LocalDate.now().toString());
        ma.setStatus("Active");
        return motherAssignmentRepository.save(ma);
    }
}
