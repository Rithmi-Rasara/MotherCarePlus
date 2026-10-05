package com.nibm.mothercare.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vaccination")
public class Vaccination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccination_id")
    private Integer vaccinationId;

    @Column(name = "mother_id", nullable = false)
    private Integer motherId;

    @Column(name = "vaccine_name")
    private String vaccineName;

    @Column(name = "vaccination_date")
    private String vaccinationDate;

    @Column(name = "next_date")
    private String nextDate;

    @Column(name = "status")
    private String status;

    public Integer getVaccinationId() {
        return vaccinationId;
    }

    public Integer getMotherId() {
        return motherId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public String getVaccinationDate() {
        return vaccinationDate;
    }

    public String getNextDate() {
        return nextDate;
    }

    public String getStatus() {
        return status;
    }

    public void setMotherId(Integer motherId) {
        this.motherId = motherId;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public void setVaccinationDate(String vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public void setNextDate(String nextDate) {
        this.nextDate = nextDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}