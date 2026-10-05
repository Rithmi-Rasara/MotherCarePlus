package com.nibm.mothercare.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a visit item in recent_visits list for midwife dashboard.
 */
public class VisitDto {

    @JsonProperty("patient_name")
    private String patientName;

    @JsonProperty("visit_date")
    private String visitDate;

    @JsonProperty("status")
    private String status;

    public VisitDto() {}

    public VisitDto(String patientName, String visitDate, String status) {
        this.patientName = patientName;
        this.visitDate = visitDate;
        this.status = status;
    }

    public String getPatientName() { return patientName; }
    public String getVisitDate()   { return visitDate; }
    public String getStatus()      { return status; }

    public void setPatientName(String patientName) { this.patientName = patientName; }
    public void setVisitDate(String visitDate)     { this.visitDate = visitDate; }
    public void setStatus(String status)          { this.status = status; }
}
