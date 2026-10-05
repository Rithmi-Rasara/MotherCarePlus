package com.nibm.mothercare.service;

import com.nibm.mothercare.entity.PregnantMother;
import com.nibm.mothercare.entity.User;
import com.nibm.mothercare.repository.PregnantMotherRepository;
import com.nibm.mothercare.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MotherRegistrationService {

    private final UserRepository userRepository;
    private final PregnantMotherRepository pregnantMotherRepository;

    public MotherRegistrationService(
            UserRepository userRepository,
            PregnantMotherRepository pregnantMotherRepository) {

        this.userRepository = userRepository;
        this.pregnantMotherRepository = pregnantMotherRepository;
    }

    public PregnantMother registerMother(
            String name,
            String email,
            String password,
            String phone,
            String address,
            String dateOfBirth) {

        // Check whether email already exists
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        if (pregnantMotherRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Mother email already exists");
        }

        // Create User
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setAddress(address);
        user.setDateOfBirth(dateOfBirth);
        user.setRole("MOTHER");
        user.setCreatedAt(LocalDate.now().toString());

        User savedUser = userRepository.save(user);

        // Create Pregnant Mother
        PregnantMother mother = new PregnantMother();
        mother.setName(name);
        mother.setEmail(email);
        mother.setPassword(password);
        mother.setPhone(phone);
        mother.setAddress(address);
        mother.setDateOfBirth(dateOfBirth);
        mother.setRegisteredAt(LocalDate.now().toString());

        // Link PregnantMother → User
        mother.setUser(savedUser);

        return pregnantMotherRepository.save(mother);
    }
}