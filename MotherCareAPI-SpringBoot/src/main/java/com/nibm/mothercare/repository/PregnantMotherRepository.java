package com.nibm.mothercare.repository;

import com.nibm.mothercare.entity.PregnantMother;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PregnantMotherRepository extends JpaRepository<PregnantMother, Integer> {

    Optional<PregnantMother> findByUser_Email(String email);
    Optional<PregnantMother> findByUser_UserId(Integer userId);
    
    // Alias for backwards compatibility
    default Optional<PregnantMother> findByEmail(String email) {
        return findByUser_Email(email);
    }
}