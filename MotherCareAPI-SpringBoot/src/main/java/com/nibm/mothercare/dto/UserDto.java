package com.nibm.mothercare.dto;

/**
 * DTO representing user data returned in the login response.
 *
 * Matches exactly what the Android app's User.java expects:
 *   user_id, name, email, phone, address, date_of_birth, role, created_at
 *
 * Note: password is intentionally excluded (same as PHP: unset($user["password"])).
 */
public class UserDto {

    private Integer user_id;
    private String  name;
    private String  email;
    private String  phone;
    private String  address;
    private String  date_of_birth;
    private String  role;
    private String  created_at;

    public UserDto() {}

    public UserDto(Integer userId, String name, String email, String phone,
                   String address, String dateOfBirth, String role, String createdAt) {
        this.user_id       = userId;
        this.name          = name;
        this.email         = email;
        this.phone         = phone;
        this.address       = address;
        this.date_of_birth = dateOfBirth;
        this.role          = role;
        this.created_at    = createdAt;
    }

    // ─── Getters ───────────────────────────────────────────────────────────────
    // Field names use snake_case to match the JSON keys the Android app expects.

    public Integer getUser_id()       { return user_id; }
    public String  getName()          { return name; }
    public String  getEmail()         { return email; }
    public String  getPhone()         { return phone; }
    public String  getAddress()       { return address; }
    public String  getDate_of_birth() { return date_of_birth; }
    public String  getRole()          { return role; }
    public String  getCreated_at()    { return created_at; }
}
