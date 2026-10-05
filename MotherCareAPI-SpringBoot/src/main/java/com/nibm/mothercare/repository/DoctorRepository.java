package com.nibm.mothercare.repository;

import com.nibm.mothercare.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Optional<Doctor> findByUser_Email(String email);
    Optional<Doctor> findByUser_UserId(Integer userId);
    
    default Optional<Doctor> findByEmail(String email) {
        return findByUser_Email(email);
    }
}
