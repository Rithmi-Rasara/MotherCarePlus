package com.nibm.mothercare.repository;

import com.nibm.mothercare.entity.ExercisePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExercisePlanRepository extends JpaRepository<ExercisePlan, Integer> {
    List<ExercisePlan> findByAdminId(Integer adminId);
}
