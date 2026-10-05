package com.nibm.mothercare.repository;

import com.nibm.mothercare.entity.NutritionGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NutritionGuideRepository extends JpaRepository<NutritionGuide, Integer> {
    List<NutritionGuide> findByAdminId(Integer adminId);
}
