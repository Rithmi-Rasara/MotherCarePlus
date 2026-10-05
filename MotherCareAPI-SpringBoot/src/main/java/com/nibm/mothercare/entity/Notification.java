package com.nibm.mothercare.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Integer notificationId;

    @Column(name = "mother_id", nullable = false)
    private Integer motherId;

    @Column(name = "title")
    private String title;

    @Column(name = "message")
    private String message;

    @Column(name = "type")
    private String type;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "is_read")
    private Boolean isRead;

    public Integer getNotificationId() {
        return notificationId;
    }

    public Integer getMotherId() {
        return motherId;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setMotherId(Integer motherId) {
        this.motherId = motherId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }
}