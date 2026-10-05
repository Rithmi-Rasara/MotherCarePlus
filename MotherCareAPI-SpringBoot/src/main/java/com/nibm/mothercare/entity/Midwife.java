package com.nibm.mothercare.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "midwife")
public class Midwife {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "midwife_id")
    private Integer midwifeId;

    @Column(name = "area")
    private String area;

    // Connect Midwife with User
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", unique = true)
    private User user;

    public Integer getMidwifeId() {
        return midwifeId;
    }

    public void setMidwifeId(Integer midwifeId) {
        this.midwifeId = midwifeId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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