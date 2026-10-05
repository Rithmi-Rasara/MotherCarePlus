package com.nibm.mothercare.repository;

import com.nibm.mothercare.entity.HomeVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeVisitRepository extends JpaRepository<HomeVisit, Integer> {
    List<HomeVisit> findByAssignmentId(Integer assignmentId);
}
