package com.nibm.mothercare.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * Response DTO for GET /api/midwife/dashboard.
 * Replaces get_midwife_dashboard.php output structure.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardResponse {

    private boolean success;
    private String message;

    @JsonProperty("total_patients")
    private int totalPatients;

    @JsonProperty("today_visits")
    private int todayVisits;

    @JsonProperty("high_risk")
    private int highRisk;

    @JsonProperty("recent_visits")
    private List<VisitDto> recentVisits = new ArrayList<>();

    public DashboardResponse() {}

    public DashboardResponse(boolean success, int totalPatients, int todayVisits, int highRisk, List<VisitDto> recentVisits) {
        this.success = success;
        this.totalPatients = totalPatients;
        this.todayVisits = todayVisits;
        this.highRisk = highRisk;
        this.recentVisits = recentVisits != null ? recentVisits : new ArrayList<>();
    }

    public boolean isSuccess()                { return success; }
    public String getMessage()                 { return message; }
    public int getTotalPatients()             { return totalPatients; }
    public int getTodayVisits()               { return todayVisits; }
    public int getHighRisk()                  { return highRisk; }
    public List<VisitDto> getRecentVisits()   { return recentVisits; }

    public void setSuccess(boolean success)                { this.success = success; }
    public void setMessage(String message)                 { this.message = message; }
    public void setTotalPatients(int totalPatients)        { this.totalPatients = totalPatients; }
    public void setTodayVisits(int todayVisits)            { this.todayVisits = todayVisits; }
    public void setHighRisk(int highRisk)                  { this.highRisk = highRisk; }
    public void setRecentVisits(List<VisitDto> recentVisits){ this.recentVisits = recentVisits; }
}
