package com.nibm.mothercare.repository;

import com.nibm.mothercare.entity.MotherAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotherAssignmentRepository extends JpaRepository<MotherAssignment, Integer> {
    List<MotherAssignment> findByMidwifeId(Integer midwifeId);
    List<MotherAssignment> findByMotherId(Integer motherId);
}
