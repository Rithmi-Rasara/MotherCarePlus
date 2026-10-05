package com.nibm.mothercare.controller;

import com.nibm.mothercare.dto.DashboardResponse;
import com.nibm.mothercare.service.MidwifeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller handling Midwife endpoints.
 *
 * Endpoint: GET /api/midwife/dashboard?user_id={id}
 * Replaces get_midwife_dashboard.php
 */
@RestController
@RequestMapping("/api/midwife")
public class MidwifeController {

    private final MidwifeService midwifeService;

    public MidwifeController(MidwifeService midwifeService) {
        this.midwifeService = midwifeService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardResponse> getDashboard(@RequestParam(value = "user_id", required = false) Integer userId) {
        if (userId == null) {
            DashboardResponse err = new DashboardResponse();
            err.setSuccess(false);
            err.setMessage("user_id is required");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }

        DashboardResponse response = midwifeService.getDashboard(userId);
        return ResponseEntity.ok(response);
    }
}
