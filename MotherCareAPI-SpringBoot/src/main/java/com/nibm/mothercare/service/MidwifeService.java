package com.nibm.mothercare.service;

import com.nibm.mothercare.dto.DashboardResponse;
import com.nibm.mothercare.dto.VisitDto;
import com.nibm.mothercare.entity.HomeVisit;
import com.nibm.mothercare.entity.MidwifeProfile;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MidwifeService {

    private final MidwifeProfileRepository midwifeProfileRepository;
    private final MotherAssignmentRepository motherAssignmentRepository;
    private final HomeVisitRepository homeVisitRepository;
    private final PregnancyRepository pregnancyRepository;
    private final PregnantMotherRepository pregnantMotherRepository;

    public MidwifeService(
            MidwifeProfileRepository midwifeProfileRepository,
            MotherAssignmentRepository motherAssignmentRepository,
            HomeVisitRepository homeVisitRepository,
            PregnancyRepository pregnancyRepository,
            PregnantMotherRepository pregnantMotherRepository) {
        this.midwifeProfileRepository = midwifeProfileRepository;
        this.motherAssignmentRepository = motherAssignmentRepository;
        this.homeVisitRepository = homeVisitRepository;
        this.pregnancyRepository = pregnancyRepository;
        this.pregnantMotherRepository = pregnantMotherRepository;
    }

    public DashboardResponse getDashboard(int userId) {
        Optional<MidwifeProfile> profile = midwifeProfileRepository.findByUserId(userId);
        int midwifeId = profile.map(MidwifeProfile::getMidwifeId).orElse(userId);

        // 1. Total Patients via MotherAssignmentRepository
        List<MotherAssignment> assignments = motherAssignmentRepository.findByMidwifeId(midwifeId);
        int totalPatients = (int) assignments.stream().map(MotherAssignment::getMotherId).distinct().count();

        // 2. Today's Visits
        String todayStr = LocalDate.now().toString();
        int todayVisits = 0;
        List<VisitDto> recentVisits = new ArrayList<>();

        for (MotherAssignment ma : assignments) {
            List<HomeVisit> visits = homeVisitRepository.findByAssignmentId(ma.getAssignmentId());
            for (HomeVisit v : visits) {
                if (todayStr.equals(v.getVisitDate())) {
                    todayVisits++;
                }
            }

            // Prepare recent visit info
            Optional<PregnantMother> motherOpt = pregnantMotherRepository.findById(ma.getMotherId());
            if (motherOpt.isPresent() && !visits.isEmpty() && recentVisits.size() < 3) {
                PregnantMother mother = motherOpt.get();
                HomeVisit lastVisit = visits.get(visits.size() - 1);
                
                Optional<Pregnancy> pregOpt = pregnancyRepository.findFirstByMotherIdOrderByPregnancyIdDesc(mother.getMotherId());
                String pregStatus = pregOpt.map(Pregnancy::getStatus).orElse("Normal");
                
                String status = (lastVisit.getObservations() == null || lastVisit.getObservations().isBlank()) ? "Scheduled" : "Completed";
                if ("High Risk".equalsIgnoreCase(pregStatus) || "High".equalsIgnoreCase(pregStatus)) {
                    status = "High Risk";
                }
                recentVisits.add(new VisitDto(mother.getName(), lastVisit.getVisitDate(), status));
            }
        }

        // 3. High Risk Patients Count
        int highRisk = 0;
        for (MotherAssignment ma : assignments) {
            Optional<Pregnancy> pOpt = pregnancyRepository.findFirstByMotherIdOrderByPregnancyIdDesc(ma.getMotherId());
            if (pOpt.isPresent()) {
                String s = pOpt.get().getStatus();
                if ("High Risk".equalsIgnoreCase(s) || "High".equalsIgnoreCase(s)) {
                    highRisk++;
                }
            }
        }

        return new DashboardResponse(true, totalPatients, todayVisits, highRisk, recentVisits);
    }
}
