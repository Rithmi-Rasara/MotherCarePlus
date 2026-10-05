package com.nibm.mothercare.repository;

import com.nibm.mothercare.entity.Pregnancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PregnancyRepository extends JpaRepository<Pregnancy, Integer> {
    List<Pregnancy> findByMotherId(Integer motherId);
    Optional<Pregnancy> findFirstByMotherIdOrderByPregnancyIdDesc(Integer motherId);
}
