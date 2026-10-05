package com.nibm.mothercare.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mother_assignment")
public class MotherAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Integer assignmentId;

    @Column(name = "midwife_id", nullable = false)
    private Integer midwifeId;

    @Column(name = "mother_id", nullable = false)
    private Integer motherId;

    @Column(name = "assigned_date")
    private String assignedDate;

    @Column(name = "status")
    private String status;

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public Integer getMidwifeId() {
        return midwifeId;
    }

    public Integer getMotherId() {
        return motherId;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setMidwifeId(Integer midwifeId) {
        this.midwifeId = midwifeId;
    }

    public void setMotherId(Integer motherId) {
        this.motherId = motherId;
    }

    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}