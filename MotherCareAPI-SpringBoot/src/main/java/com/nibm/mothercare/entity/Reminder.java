package com.nibm.mothercare.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reminder")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reminder_id")
    private Integer reminderId;

    @Column(name = "prescription_id", nullable = false)
    private Integer prescriptionId;

    @Column(name = "mother_id", nullable = false)
    private Integer motherId;

    @Column(name = "type")
    private String type;

    @Column(name = "reminder_date")
    private String reminderDate;

    @Column(name = "reminder_time")
    private String reminderTime;

    @Column(name = "status")
    private String status;

    public Integer getReminderId() {
        return reminderId;
    }

    public Integer getPrescriptionId() {
        return prescriptionId;
    }

    public Integer getMotherId() {
        return motherId;
    }

    public String getType() {
        return type;
    }

    public String getReminderDate() {
        return reminderDate;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public String getStatus() {
        return status;
    }

    public void setPrescriptionId(Integer prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public void setMotherId(Integer motherId) {
        this.motherId = motherId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setReminderDate(String reminderDate) {
        this.reminderDate = reminderDate;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}