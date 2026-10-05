package com.nibm.mothercare.repository;

import com.nibm.mothercare.entity.MidwifeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for the `midwife_profile` table.
 * Replaces: SELECT midwife_id FROM midwife_profile WHERE user_id = ? (get_midwife_dashboard.php)
 */
@Repository
public interface MidwifeProfileRepository extends JpaRepository<MidwifeProfile, Integer> {

    /**
     * Resolve user_id to midwife profile.
     * Equivalent PHP: SELECT midwife_id FROM midwife_profile WHERE user_id = ?
     */
    Optional<MidwifeProfile> findByUserId(Integer userId);
}
