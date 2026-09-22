package com.nibm.mothercare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MidwifeDashboardActivity extends AppCompatActivity {

    // Stats
    private TextView txtMidwifeName;
    private TextView txtClinicArea;
    private TextView txtDateValue;
    private TextView txtTotalPatients;
    private TextView txtTodayVisits;
    private TextView txtHighRisk;

    // Quick Actions
    private LinearLayout btnAddPatient;
    private LinearLayout btnScheduleVisit;
    private LinearLayout btnHealthRecords;

    // Visit cards
    private LinearLayout visitCard1;
    private LinearLayout visitCard2;
    private LinearLayout visitCard3;

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

        txtMidwifeName      = findViewById(R.id.txtMidwifeName);
        txtClinicArea       = findViewById(R.id.txtClinicArea);
        txtDateValue        = findViewById(R.id.txtDateValue);
        txtTotalPatients    = findViewById(R.id.txtTotalPatients);
        txtTodayVisits      = findViewById(R.id.txtTodayVisits);
        txtHighRisk         = findViewById(R.id.txtHighRisk);

        btnAddPatient       = findViewById(R.id.btnAddPatient);
        btnScheduleVisit    = findViewById(R.id.btnScheduleVisit);
        btnHealthRecords    = findViewById(R.id.btnHealthRecords);

        visitCard1          = findViewById(R.id.visitCard1);
        visitCard2          = findViewById(R.id.visitCard2);
        visitCard3          = findViewById(R.id.visitCard3);
        vaccineCard         = findViewById(R.id.vaccineCard);
        txtSeeAll           = findViewById(R.id.txtSeeAll);

        navHome             = findViewById(R.id.navHome);
        navPatients         = findViewById(R.id.navPatients);
        navVisits           = findViewById(R.id.navVisits);
        navProfile          = findViewById(R.id.navProfile);

        // ----------------------------------------
        // Set Midwife Name & Date
        // ----------------------------------------

        txtMidwifeName.setText(midwifeName);
        txtClinicArea.setText("MOH Clinic · Colombo District");

        String today = new SimpleDateFormat(
                "EEEE, dd MMMM yyyy", Locale.ENGLISH
        ).format(new Date());
        txtDateValue.setText(today);

        // ----------------------------------------
        // Dashboard Stats (static until API ready)
        // ----------------------------------------

        txtTotalPatients.setText("24");
        txtTodayVisits.setText("6");
        txtHighRisk.setText("3");

        // ----------------------------------------
        // Quick Actions
        // ----------------------------------------

        btnAddPatient.setOnClickListener(v ->
                Toast.makeText(
                        MidwifeDashboardActivity.this,
                        "Add Patient - Coming Soon",
                        Toast.LENGTH_SHORT
                ).show()
        );

        btnScheduleVisit.setOnClickListener(v ->
                Toast.makeText(
                        MidwifeDashboardActivity.this,
                        "Schedule Visit - Coming Soon",
                        Toast.LENGTH_SHORT
                ).show()
        );

        btnHealthRecords.setOnClickListener(v ->
                Toast.makeText(
                        MidwifeDashboardActivity.this,
                        "Health Records - Coming Soon",
                        Toast.LENGTH_SHORT
                ).show()
        );

        // ----------------------------------------
        // Visit Cards
        // ----------------------------------------

        visitCard1.setOnClickListener(v ->
                Toast.makeText(
                        MidwifeDashboardActivity.this,
                        "Kamala Perera - Visit Details",
                        Toast.LENGTH_SHORT
                ).show()
        );

        visitCard2.setOnClickListener(v ->
                Toast.makeText(
                        MidwifeDashboardActivity.this,
                        "Nilmini Silva - Visit Details",
                        Toast.LENGTH_SHORT
                ).show()
        );

        visitCard3.setOnClickListener(v ->
                Toast.makeText(
                        MidwifeDashboardActivity.this,
                        "Priya Fernando - HIGH RISK - Immediate Attention",
                        Toast.LENGTH_LONG
                ).show()
        );

        vaccineCard.setOnClickListener(v ->
                Toast.makeText(
                        MidwifeDashboardActivity.this,
                        "Vaccination Schedule - Coming Soon",
                        Toast.LENGTH_SHORT
                ).show()
        );

        txtSeeAll.setOnClickListener(v ->
                Toast.makeText(
                        MidwifeDashboardActivity.this,
                        "All Visits - Coming Soon",
                        Toast.LENGTH_SHORT
                ).show()
        );

        // ----------------------------------------
        // Bottom Navigation
        // ----------------------------------------

        navHome.setOnClickListener(v -> { /* already on home */ });

        navPatients.setOnClickListener(v ->
                Toast.makeText(
                        MidwifeDashboardActivity.this,
                        "Patients - Coming Soon",
                        Toast.LENGTH_SHORT
                ).show()
        );

        navVisits.setOnClickListener(v ->
                Toast.makeText(
                        MidwifeDashboardActivity.this,
                        "Visits - Coming Soon",
                        Toast.LENGTH_SHORT
                ).show()
        );

        navProfile.setOnClickListener(v ->
                Toast.makeText(
                        MidwifeDashboardActivity.this,
                        "Profile - Coming Soon",
                        Toast.LENGTH_SHORT
                ).show()
        );
    }
}
