package com.nibm.mothercare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MotherDashboardActivity extends AppCompatActivity {

    private LinearLayout btnMedication;
    private LinearLayout btnVaccination;
    private LinearLayout btnReports;
    private LinearLayout btnEmergency;

    private LinearLayout navHome;
    private LinearLayout navAppointments;
    private LinearLayout navHealth;
    private LinearLayout navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mother_dashboard);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // -----------------------------
        // Get logged-in mother's name
        // -----------------------------

        String motherName = getIntent().getStringExtra("name");

        if (motherName == null || motherName.trim().isEmpty()) {
            motherName = "Mother";
        }

        android.widget.TextView txtMotherName =
                findViewById(R.id.txtMotherName);

        txtMotherName.setText(motherName);


        // -----------------------------
        // Find Views
        // -----------------------------

        btnMedication = findViewById(R.id.btnMedication);
        btnVaccination = findViewById(R.id.btnVaccination);
        btnReports = findViewById(R.id.btnReports);
        btnEmergency = findViewById(R.id.btnEmergency);

        navHome = findViewById(R.id.navHome);
        navAppointments = findViewById(R.id.navAppointments);
        navHealth = findViewById(R.id.navHealth);
        navProfile = findViewById(R.id.navProfile);


        // -----------------------------
        // Quick Access
        // -----------------------------

        btnMedication.setOnClickListener(v -> {

            Toast.makeText(
                    MotherDashboardActivity.this,
                    "Medication",
                    Toast.LENGTH_SHORT
            ).show();

        });


        btnVaccination.setOnClickListener(v -> {

            Toast.makeText(
                    MotherDashboardActivity.this,
                    "Vaccination",
                    Toast.LENGTH_SHORT
            ).show();

        });


        btnReports.setOnClickListener(v -> {

            Toast.makeText(
                    MotherDashboardActivity.this,
                    "Medical Reports",
                    Toast.LENGTH_SHORT
            ).show();

        });


        // -----------------------------
        // Emergency
        // -----------------------------

        btnEmergency.setOnClickListener(v -> {

            Toast.makeText(
                    MotherDashboardActivity.this,
                    "Emergency assistance",
                    Toast.LENGTH_SHORT
            ).show();

        });


        // -----------------------------
        // Bottom Navigation
        // -----------------------------

        navHome.setOnClickListener(v -> {

            Toast.makeText(
                    MotherDashboardActivity.this,
                    "Home",
                    Toast.LENGTH_SHORT
            ).show();

        });


        navAppointments.setOnClickListener(v -> {

            Toast.makeText(
                    MotherDashboardActivity.this,
                    "Appointments",
                    Toast.LENGTH_SHORT
            ).show();

        });


        navHealth.setOnClickListener(v -> {

            Toast.makeText(
                    MotherDashboardActivity.this,
                    "Health",
                    Toast.LENGTH_SHORT
            ).show();

        });


        navProfile.setOnClickListener(v -> {

            Toast.makeText(
                    MotherDashboardActivity.this,
                    "Profile",
                    Toast.LENGTH_SHORT
            ).show();

        });

    }
}