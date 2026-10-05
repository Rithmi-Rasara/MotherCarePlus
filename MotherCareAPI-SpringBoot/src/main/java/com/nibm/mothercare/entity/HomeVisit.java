package com.nibm.mothercare.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "home_visit")
public class HomeVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private Integer visitId;

    @Column(name = "assignment_id", nullable = false)
    private Integer assignmentId;

    @Column(name = "visit_date")
    private String visitDate;

    @Column(name = "observations")
    private String observations;

    @Column(name = "advice")
    private String advice;

    @Column(name = "pregnancy_progress")
    private String pregnancyProgress;

    public Integer getVisitId() {
        return visitId;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public String getObservations() {
        return observations;
    }

    public String getAdvice() {
        return advice;
    }

    public String getPregnancyProgress() {
        return pregnancyProgress;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public void setPregnancyProgress(String pregnancyProgress) {
        this.pregnancyProgress = pregnancyProgress;
    }
}