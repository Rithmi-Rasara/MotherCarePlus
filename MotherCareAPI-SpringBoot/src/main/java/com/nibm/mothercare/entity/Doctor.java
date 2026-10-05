package com.nibm.mothercare.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Integer doctorId;

    @Column(name = "specialization")
    private String specialization;

    // Connect Doctor with User
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id",
            unique = true
    )
    private User user;

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Helper delegate methods pointing to User entity
    public String getName() {
        return user != null ? user.getName() : null;
    }

    public void setName(String name) {
        if (user != null) user.setName(name);
    }

    public String getEmail() {
        return user != null ? user.getEmail() : null;
    }

    public void setEmail(String email) {
        if (user != null) user.setEmail(email);
    }

    public String getPassword() {
        return user != null ? user.getPassword() : null;
    }

    public void setPassword(String password) {
        if (user != null) user.setPassword(password);
    }

    public String getPhone() {
        return user != null ? user.getPhone() : null;
    }

    public void setPhone(String phone) {
        if (user != null) user.setPhone(phone);
    }
}