package com.nibm.mothercare.repository;

import com.nibm.mothercare.entity.MedicalReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalReportRepository extends JpaRepository<MedicalReport, Integer> {
    List<MedicalReport> findByMotherId(Integer motherId);
    List<MedicalReport> findByDoctorId(Integer doctorId);
}
