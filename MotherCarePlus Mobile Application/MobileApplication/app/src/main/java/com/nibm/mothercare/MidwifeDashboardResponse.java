package com.nibm.mothercare;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MidwifeDashboardResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("total_patients")
    private int totalPatients;

    @SerializedName("today_visits")
    private int todayVisits;

    @SerializedName("high_risk")
    private int highRisk;

    @SerializedName("recent_visits")
    private List<Visit> recentVisits;

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public int getTotalPatients() { return totalPatients; }
    public int getTodayVisits() { return todayVisits; }
    public int getHighRisk() { return highRisk; }
    public List<Visit> getRecentVisits() { return recentVisits; }
}
