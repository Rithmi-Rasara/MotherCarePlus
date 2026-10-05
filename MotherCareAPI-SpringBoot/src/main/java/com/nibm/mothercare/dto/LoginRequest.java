package com.nibm.mothercare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Request body for POST /api/auth/login.
 *
 * Equivalent to PHP:
 *   $input = json_decode(file_get_contents("php://input"), true);
 *   $email    = $input["email"];
 *   $password = $input["password"];
 */
public class LoginRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    // ─── Getters & Setters ──────────────────────────────────────────────────────

    public String getEmail()    { return email; }
    public String getPassword() { return password; }

    public void setEmail(String email)       { this.email = email; }
    public void setPassword(String password) { this.password = password; }
}
