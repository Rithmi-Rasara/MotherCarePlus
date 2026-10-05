package com.nibm.mothercare.repository;

import com.nibm.mothercare.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Integer> {
    List<Diagnosis> findByDoctorId(Integer doctorId);
    List<Diagnosis> findByAppointmentId(Integer appointmentId);
    Optional<Diagnosis> findFirstByAppointmentIdAndDoctorIdOrderByDiagnosisIdDesc(Integer appointmentId, Integer doctorId);
}
