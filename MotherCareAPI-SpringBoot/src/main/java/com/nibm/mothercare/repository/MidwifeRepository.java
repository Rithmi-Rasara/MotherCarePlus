package com.nibm.mothercare.repository;

import com.nibm.mothercare.entity.Midwife;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MidwifeRepository extends JpaRepository<Midwife, Integer> {
    Optional<Midwife> findByUser_Email(String email);
    Optional<Midwife> findByUser_UserId(Integer userId);
    
    default Optional<Midwife> findByEmail(String email) {
        return findByUser_Email(email);
    }
}
