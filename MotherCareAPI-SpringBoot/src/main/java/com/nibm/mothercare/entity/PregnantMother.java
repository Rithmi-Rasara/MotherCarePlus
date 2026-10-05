package com.nibm.mothercare.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pregnant_mother")
public class PregnantMother {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mother_id")
    private Integer motherId;

    // Connect Pregnant Mother with User
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id",
            unique = true
    )
    private User user;

    public Integer getMotherId() {
        return motherId;
    }

    public void setMotherId(Integer motherId) {
        this.motherId = motherId;
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

    public String getAddress() {
        return user != null ? user.getAddress() : null;
    }

    public void setAddress(String address) {
        if (user != null) user.setAddress(address);
    }

    public String getDateOfBirth() {
        return user != null ? user.getDateOfBirth() : null;
    }

    public void setDateOfBirth(String dateOfBirth) {
        if (user != null) user.setDateOfBirth(dateOfBirth);
    }

    public String getRegisteredAt() {
        return user != null ? user.getCreatedAt() : null;
    }

    public void setRegisteredAt(String registeredAt) {
        if (user != null) user.setCreatedAt(registeredAt);
    }
}