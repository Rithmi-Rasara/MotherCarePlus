package com.nibm.mothercare.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "health_record")
public class HealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_id")
    private Integer healthId;

    @Column(name = "pregnancy_id", nullable = false)
    private Integer pregnancyId;

    @Column(name = "record_date")
    private String recordDate;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "blood_pressure")
    private String bloodPressure;

    @Column(name = "blood_sugar")
    private Double bloodSugar;

    @Column(name = "water_intake")
    private Double waterIntake;

    @Column(name = "notes")
    private String notes;

    public Integer getHealthId() {
        return healthId;
    }

    public Integer getPregnancyId() {
        return pregnancyId;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public Double getWeight() {
        return weight;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public Double getBloodSugar() {
        return bloodSugar;
    }

    public Double getWaterIntake() {
        return waterIntake;
    }

    public String getNotes() {
        return notes;
    }

    public void setPregnancyId(Integer pregnancyId) {
        this.pregnancyId = pregnancyId;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public void setBloodSugar(Double bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public void setWaterIntake(Double waterIntake) {
        this.waterIntake = waterIntake;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}