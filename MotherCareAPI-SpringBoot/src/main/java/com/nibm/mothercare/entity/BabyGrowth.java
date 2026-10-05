package com.nibm.mothercare.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "baby_growth")
public class BabyGrowth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "growth_id")
    private Integer growthId;

    @Column(name = "pregnancy_id", nullable = false)
    private Integer pregnancyId;

    @Column(name = "week")
    private Integer week;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "length")
    private Double length;

    @Column(name = "notes")
    private String notes;

    public Integer getGrowthId() {
        return growthId;
    }

    public Integer getPregnancyId() {
        return pregnancyId;
    }

    public Integer getWeek() {
        return week;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getLength() {
        return length;
    }

    public String getNotes() {
        return notes;
    }

    public void setPregnancyId(Integer pregnancyId) {
        this.pregnancyId = pregnancyId;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}