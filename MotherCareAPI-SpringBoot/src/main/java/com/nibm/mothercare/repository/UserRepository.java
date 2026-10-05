package com.nibm.mothercare.repository;

import com.nibm.mothercare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for the `user` table.
 * Replaces: SELECT * FROM user WHERE email = ? (login.php)
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Find a user by email address (used during login).
     * Equivalent to: SELECT * FROM `user` WHERE email = ? LIMIT 1
     */
    Optional<User> findByEmail(String email);
}
