package com.nibm.mothercare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MidwifeDashboardActivity extends AppCompatActivity {

    // Header & Stats
    private RelativeLayout topHeader;
    private TextView txtMidwifeName;
    private TextView txtClinicArea;
    private TextView txtDateValue;
    private TextView txtTotalPatients;
    private TextView txtTodayVisits;
    private TextView txtHighRisk;
    private ImageView btnMidwifeNotification;

    // Quick Actions
    private LinearLayout btnAddPatient;
    private LinearLayout btnScheduleVisit;
    private LinearLayout btnHealthRecords;

    // Visit cards
    private LinearLayout visitCard1;
    private TextView txtVisit1Name, txtVisit1Detail, txtVisit1Status;

    private LinearLayout visitCard2;
    private TextView txtVisit2Name, txtVisit2Detail, txtVisit2Status;

    private LinearLayout visitCard3;
    private TextView txtVisit3Name, txtVisit3Detail, txtVisit3Status;

    // Vaccine card
    private LinearLayout vaccineCard;

    // See all
    private TextView txtSeeAll;

    // Bottom Navigation
    private LinearLayout navHome;
    private LinearLayout navPatients;
    private LinearLayout navVisits;
    private LinearLayout navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_midwife_dashboard);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        topHeader = findViewById(R.id.topHeader);

        // ----------------------------------------
        // Get logged-in midwife details from Intent
        // ----------------------------------------
        String midwifeName = getIntent().getStringExtra("name");
        String midwifeEmail = getIntent().getStringExtra("email");

        if (midwifeName == null || midwifeName.trim().isEmpty()) {
            midwifeName = "Midwife";
        }

        // ----------------------------------------
        // Find Views
        // ----------------------------------------
        txtMidwifeName = findViewById(R.id.txtMidwifeName);
        txtClinicArea = findViewById(R.id.txtClinicArea);
        txtDateValue = findViewById(R.id.txtDateValue);
        btnMidwifeNotification = findViewById(R.id.btnMidwifeNotification);

        txtTotalPatients = findViewById(R.id.txtTotalPatients);
        txtTodayVisits = findViewById(R.id.txtTodayVisits);
        txtHighRisk = findViewById(R.id.txtHighRisk);

        btnAddPatient = findViewById(R.id.btnAddPatient);
        btnScheduleVisit = findViewById(R.id.btnScheduleVisit);
        btnHealthRecords = findViewById(R.id.btnHealthRecords);

        visitCard1 = findViewById(R.id.visitCard1);
        txtVisit1Name = findViewById(R.id.txtVisit1Name);
        txtVisit1Detail = findViewById(R.id.txtVisit1Detail);
        txtVisit1Status = findViewById(R.id.txtVisit1Status);

        visitCard2 = findViewById(R.id.visitCard2);
        txtVisit2Name = findViewById(R.id.txtVisit2Name);
        txtVisit2Detail = findViewById(R.id.txtVisit2Detail);
        txtVisit2Status = findViewById(R.id.txtVisit2Status);

        visitCard3 = findViewById(R.id.visitCard3);
        txtVisit3Name = findViewById(R.id.txtVisit3Name);
        txtVisit3Detail = findViewById(R.id.txtVisit3Detail);
        txtVisit3Status = findViewById(R.id.txtVisit3Status);

        vaccineCard = findViewById(R.id.vaccineCard);
        txtSeeAll = findViewById(R.id.txtSeeAll);

        navHome = findViewById(R.id.navHome);
        navPatients = findViewById(R.id.navPatients);
        navVisits = findViewById(R.id.navVisits);
        navProfile = findViewById(R.id.navProfile);

        // ----------------------------------------
        // Set Midwife Name & Clinic
        // ----------------------------------------
        txtMidwifeName.setText(midwifeName);
        txtClinicArea.setText("MOH Clinic • Galle / Colombo District");

        // ----------------------------------------
        // Set Current Date
        // ----------------------------------------
        String today = new SimpleDateFormat(
                "EEEE, dd MMMM yyyy",
                Locale.ENGLISH
        ).format(new Date());

        txtDateValue.setText(today);

        // Set proposal stats defaults initially
        txtTotalPatients.setText("1,256");
        txtTodayVisits.setText("5");
        txtHighRisk.setText("3");

        // ----------------------------------------
        // Get Logged-in Midwife User ID & Stats
        // ----------------------------------------
        int userId = getIntent().getIntExtra("user_id", 0);

        if (userId > 0) {
            fetchDashboardStats(userId);
        }

        if (btnMidwifeNotification != null) {
            btnMidwifeNotification.setOnClickListener(v ->
                    Toast.makeText(MidwifeDashboardActivity.this, "Notifications: 3 High-Risk Alerts Pending", Toast.LENGTH_SHORT).show()
            );
        }

        // ----------------------------------------
        // Quick Action: REGISTER MOTHER
        // ----------------------------------------
        btnAddPatient.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MidwifeDashboardActivity.this,
                    MotherRegistrationActivity.class
            );
            intent.putExtra("midwife_user_id", userId);
            startActivity(intent);
        });

        // Schedule Visit
        btnScheduleVisit.setOnClickListener(v -> {
            Intent intent = new Intent(MidwifeDashboardActivity.this, ScheduleVisitActivity.class);
            startActivity(intent);
        });

        // Health Records
        btnHealthRecords.setOnClickListener(v -> {
            Intent intent = new Intent(MidwifeDashboardActivity.this, HealthRecordsActivity.class);
            startActivity(intent);
        });

        // Visit Cards Listeners
        visitCard1.setOnClickListener(v -> {
            Intent intent = new Intent(MidwifeDashboardActivity.this, ScheduleVisitActivity.class);
            intent.putExtra("selected_patient", "Kamala Perera");
            startActivity(intent);
        });

        visitCard2.setOnClickListener(v -> {
            Intent intent = new Intent(MidwifeDashboardActivity.this, ScheduleVisitActivity.class);
            intent.putExtra("selected_patient", "Nilmini Silva");
            startActivity(intent);
        });

        visitCard3.setOnClickListener(v -> {
            Intent intent = new Intent(MidwifeDashboardActivity.this, ScheduleVisitActivity.class);
            intent.putExtra("selected_patient", "Priya Fernando");
            startActivity(intent);
        });

        // Vaccine Card
        vaccineCard.setOnClickListener(v -> {
            Intent intent = new Intent(MidwifeDashboardActivity.this, HealthRecordsActivity.class);
            startActivity(intent);
        });

        // See All Visits
        txtSeeAll.setOnClickListener(v -> {
            Intent intent = new Intent(MidwifeDashboardActivity.this, PatientsListActivity.class);
            startActivity(intent);
        });

        // Bottom Navigation
        navHome.setOnClickListener(v -> {
            // Already on Home
        });

        navPatients.setOnClickListener(v -> {
            Intent intent = new Intent(MidwifeDashboardActivity.this, PatientsListActivity.class);
            startActivity(intent);
        });

        navVisits.setOnClickListener(v -> {
            Intent intent = new Intent(MidwifeDashboardActivity.this, ScheduleVisitActivity.class);
            startActivity(intent);
        });

        final String finalMidwifeName = midwifeName;
        navProfile.setOnClickListener(v -> {
            Intent intent = new Intent(MidwifeDashboardActivity.this, MidwifeProfileActivity.class);
            intent.putExtra("name", finalMidwifeName);
            startActivity(intent);
        });
    }

    // =========================================================
    // FETCH DASHBOARD DATA
    // =========================================================
    private void fetchDashboardStats(int userId) {
        ApiService apiService = RetrofitClient.getApiService();
        retrofit2.Call<MidwifeDashboardResponse> call =
                apiService.getMidwifeDashboard(userId);

        call.enqueue(new retrofit2.Callback<MidwifeDashboardResponse>() {
            @Override
            public void onResponse(
                    retrofit2.Call<MidwifeDashboardResponse> call,
                    retrofit2.Response<MidwifeDashboardResponse> response) {

                if (response.isSuccessful() && response.body() != null) {
                    MidwifeDashboardResponse dashboardResponse = response.body();

                    if (dashboardResponse.isSuccess()) {
                        int total = dashboardResponse.getTotalPatients();
                        int visitsCount = dashboardResponse.getTodayVisits();
                        int highRiskCount = dashboardResponse.getHighRisk();

                        txtTotalPatients.setText(total > 0 ? String.valueOf(total) : "1,256");
                        txtTodayVisits.setText(visitsCount > 0 ? String.valueOf(visitsCount) : "5");
                        txtHighRisk.setText(highRiskCount > 0 ? String.valueOf(highRiskCount) : "3");

                        java.util.List<Visit> visits = dashboardResponse.getRecentVisits();
                        if (visits != null && !visits.isEmpty()) {
                            if (visits.size() > 0) {
                                visitCard1.setVisibility(android.view.View.VISIBLE);
                                txtVisit1Name.setText(visits.get(0).getPatientName());
                                txtVisit1Detail.setText(visits.get(0).getVisitDate());
                                setVisitStatusStyle(txtVisit1Status, visits.get(0).getStatus());
                            }
                            if (visits.size() > 1) {
                                visitCard2.setVisibility(android.view.View.VISIBLE);
                                txtVisit2Name.setText(visits.get(1).getPatientName());
                                txtVisit2Detail.setText(visits.get(1).getVisitDate());
                                setVisitStatusStyle(txtVisit2Status, visits.get(1).getStatus());
                            }
                            if (visits.size() > 2) {
                                visitCard3.setVisibility(android.view.View.VISIBLE);
                                txtVisit3Name.setText(visits.get(2).getPatientName());
                                txtVisit3Detail.setText(visits.get(2).getVisitDate());
                                setVisitStatusStyle(txtVisit3Status, visits.get(2).getStatus());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(
                    retrofit2.Call<MidwifeDashboardResponse> call,
                    Throwable t) {
                // Keep proposal stats on network failure
            }
        });
    }

    private void setVisitStatusStyle(TextView txtStatus, String status) {
        txtStatus.setText(status);
        if ("High Risk".equalsIgnoreCase(status)) {
            txtStatus.setBackgroundResource(R.drawable.emergency_background);
            txtStatus.setTextColor(android.graphics.Color.WHITE);
        } else {
            txtStatus.setBackgroundResource(R.drawable.midwife_stat_background);
            txtStatus.setTextColor(android.graphics.Color.parseColor("#985AB5"));
        }
    }
}