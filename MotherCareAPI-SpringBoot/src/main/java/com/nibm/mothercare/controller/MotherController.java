package com.nibm.mothercare.controller;

import com.nibm.mothercare.entity.PregnantMother;
import com.nibm.mothercare.service.MotherRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mothers")
public class MotherController {

    private final MotherRegistrationService motherRegistrationService;

    public MotherController(
            MotherRegistrationService motherRegistrationService) {
        this.motherRegistrationService = motherRegistrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerMother(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String dateOfBirth) {

        try {

            PregnantMother mother =
                    motherRegistrationService.registerMother(
                            name,
                            email,
                            password,
                            phone,
                            address,
                            dateOfBirth
                    );

            return ResponseEntity.ok(mother);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}