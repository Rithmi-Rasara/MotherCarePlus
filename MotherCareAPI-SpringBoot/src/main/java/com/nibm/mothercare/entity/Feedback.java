package com.nibm.mothercare.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Integer feedbackId;

    @Column(name = "mother_id", nullable = false)
    private Integer motherId;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "message")
    private String message;

    @Column(name = "created_at")
    private String createdAt;

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public Integer getMotherId() {
        return motherId;
    }

    public Integer getRating() {
        return rating;
    }

    public String getMessage() {
        return message;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setMotherId(Integer motherId) {
        this.motherId = motherId;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}