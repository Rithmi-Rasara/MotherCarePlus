package com.nibm.mothercare;

import com.google.gson.annotations.SerializedName;

public class Visit {
    @SerializedName("patient_name")
    private String patientName;

    @SerializedName("visit_date")
    private String visitDate;

    @SerializedName("status")
    private String status;

    public String getPatientName() { return patientName; }
    public String getVisitDate() { return visitDate; }
    public String getStatus() { return status; }
}
