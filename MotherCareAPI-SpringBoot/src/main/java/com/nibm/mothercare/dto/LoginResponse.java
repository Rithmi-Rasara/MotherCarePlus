package com.nibm.mothercare.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Response DTO for POST /api/auth/login.
 * Matches PHP output: { "success": true/false, "message": "...", "user": { ... } }
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {

    private boolean success;
    private String message;
    private UserDto user;

    public LoginResponse() {}

    public LoginResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public LoginResponse(boolean success, String message, UserDto user) {
        this.success = success;
        this.message = message;
        this.user = user;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public UserDto getUser()    { return user; }

    public void setSuccess(boolean success) { this.success = success; }
    public void setMessage(String message) { this.message = message; }
    public void setUser(UserDto user)       { this.user = user; }
}
