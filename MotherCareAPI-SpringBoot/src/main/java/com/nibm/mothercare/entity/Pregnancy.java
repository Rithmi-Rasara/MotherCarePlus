package com.nibm.mothercare.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pregnancy")
public class Pregnancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pregnancy_id")
    private Integer pregnancyId;

    @Column(name = "mother_id", nullable = false)
    private Integer motherId;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "due_date")
    private String dueDate;

    @Column(name = "current_week")
    private Integer currentWeek;

    @Column(name = "status")
    private String status;

    public Integer getPregnancyId() {
        return pregnancyId;
    }

    public Integer getMotherId() {
        return motherId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public Integer getCurrentWeek() {
        return currentWeek;
    }

    public String getStatus() {
        return status;
    }

    public void setMotherId(Integer motherId) {
        this.motherId = motherId;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setCurrentWeek(Integer currentWeek) {
        this.currentWeek = currentWeek;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}