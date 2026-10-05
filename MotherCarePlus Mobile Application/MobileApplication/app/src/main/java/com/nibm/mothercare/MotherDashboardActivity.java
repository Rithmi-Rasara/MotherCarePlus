package com.nibm.mothercare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MotherDashboardActivity extends AppCompatActivity {

    private RelativeLayout topHeader;
    private TextView txtMotherName;
    private TextView txtTrimester;
    private TextView txtWeeks;
    private TextView txtBabySize;
    private TextView txtDueDate;
    private ProgressBar pregnancyProgress;

    // Trackers
    private LinearLayout btnWaterIntake;
    private TextView txtWaterIntake;
    private double currentWaterLiters = 1.8;

    private LinearLayout btnKickCounter;
    private TextView txtKickCount;
    private int kickCount = 12;

    private LinearLayout bloodPressureCard;
    private TextView txtBloodPressure;

    private LinearLayout weightCard;
    private TextView txtWeight;

    private LinearLayout btnBloodSugar;
    private TextView txtBloodSugar;

    // Appointment Card
    private LinearLayout appointmentCard;
    private TextView txtDoctorName;
    private TextView txtAppointmentDate;

    // Quick Access Services
    private LinearLayout btnMedication;
    private LinearLayout btnVaccination;
    private LinearLayout btnReports;
    private LinearLayout btnDoctorChat;
    private LinearLayout btnContractionTimer;
    private LinearLayout btnNearbyHospitals;
    private LinearLayout btnEmergency;

    // Top Header & Bottom Nav
    private ImageButton btnNotification;
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

        topHeader = findViewById(R.id.topHeader);

        // -----------------------------
        // Find Views
        // -----------------------------
        txtMotherName = findViewById(R.id.txtMotherName);
        txtTrimester = findViewById(R.id.txtTrimester);
        txtWeeks = findViewById(R.id.txtWeeks);
        txtBabySize = findViewById(R.id.txtBabySize);
        txtDueDate = findViewById(R.id.txtDueDate);
        pregnancyProgress = findViewById(R.id.pregnancyProgress);

        btnWaterIntake = findViewById(R.id.btnWaterIntake);
        txtWaterIntake = findViewById(R.id.txtWaterIntake);

        btnKickCounter = findViewById(R.id.btnKickCounter);
        txtKickCount = findViewById(R.id.txtKickCount);

        bloodPressureCard = findViewById(R.id.bloodPressureCard);
        txtBloodPressure = findViewById(R.id.txtBloodPressure);

        weightCard = findViewById(R.id.weightCard);
        txtWeight = findViewById(R.id.txtWeight);

        btnBloodSugar = findViewById(R.id.btnBloodSugar);
        txtBloodSugar = findViewById(R.id.txtBloodSugar);

        appointmentCard = findViewById(R.id.appointmentCard);
        txtDoctorName = findViewById(R.id.txtDoctorName);
        txtAppointmentDate = findViewById(R.id.txtAppointmentDate);

        btnMedication = findViewById(R.id.btnMedication);
        btnVaccination = findViewById(R.id.btnVaccination);
        btnReports = findViewById(R.id.btnReports);
        btnDoctorChat = findViewById(R.id.btnDoctorChat);
        btnContractionTimer = findViewById(R.id.btnContractionTimer);
        btnNearbyHospitals = findViewById(R.id.btnNearbyHospitals);
        btnEmergency = findViewById(R.id.btnEmergency);

        btnNotification = findViewById(R.id.btnNotification);
        navHome = findViewById(R.id.navHome);
        navAppointments = findViewById(R.id.navAppointments);
        navHealth = findViewById(R.id.navHealth);
        navProfile = findViewById(R.id.navProfile);

        // -----------------------------
        // Get Intent Data
        // -----------------------------
        String motherName = getIntent().getStringExtra("name");
        if (motherName == null || motherName.trim().isEmpty()) {
            motherName = "Mother";
        }
        txtMotherName.setText(motherName);

        // Set dynamic pregnancy values
        txtTrimester.setText("2nd Trimester • Normal Progress");
        txtWeeks.setText("28 Weeks");
        txtBabySize.setText("Baby Size: ~1.1 kg • Length ~37.6 cm (Eggplant 🍆)");
        txtDueDate.setText("Due: 20 Dec 2026");
        pregnancyProgress.setProgress(28);

        // -----------------------------
        // Interactive Trackers
        // -----------------------------
        btnWaterIntake.setOnClickListener(v -> {
            currentWaterLiters += 0.25;
            if (currentWaterLiters > 3.5) {
                currentWaterLiters = 0.25;
            }
            txtWaterIntake.setText(String.format("%.1fL / 2.5L", currentWaterLiters));
            Toast.makeText(MotherDashboardActivity.this, "Logged +250ml Water 💧", Toast.LENGTH_SHORT).show();
        });

        btnKickCounter.setOnClickListener(v -> {
            kickCount++;
            txtKickCount.setText(kickCount + " Kicks");
            Toast.makeText(MotherDashboardActivity.this, "Baby Kick Logged! 👶 Total: " + kickCount, Toast.LENGTH_SHORT).show();
        });

        bloodPressureCard.setOnClickListener(v ->
                Toast.makeText(MotherDashboardActivity.this, "Blood Pressure Log: 120/80 mmHg (Normal)", Toast.LENGTH_SHORT).show()
        );

        weightCard.setOnClickListener(v ->
                Toast.makeText(MotherDashboardActivity.this, "Weight Log: 64.0 kg", Toast.LENGTH_SHORT).show()
        );

        btnBloodSugar.setOnClickListener(v ->
                Toast.makeText(MotherDashboardActivity.this, "Blood Sugar: 95 mg/dL (Normal Fasting)", Toast.LENGTH_SHORT).show()
        );

        appointmentCard.setOnClickListener(v ->
                Toast.makeText(MotherDashboardActivity.this, "Appointment Details: Dr. Sarah Fernando at MOH Clinic", Toast.LENGTH_SHORT).show()
        );

        // -----------------------------
        // Services Actions
        // -----------------------------
        btnMedication.setOnClickListener(v ->
                Toast.makeText(MotherDashboardActivity.this, "Medicine Reminder: Iron & Folic Acid pills scheduled at 8:00 PM", Toast.LENGTH_LONG).show()
        );

        btnVaccination.setOnClickListener(v ->
                Toast.makeText(MotherDashboardActivity.this, "Vaccine Schedule: Tetanus Toxoid (TT) Dose 2 scheduled next week", Toast.LENGTH_LONG).show()
        );

        btnReports.setOnClickListener(v ->
                Toast.makeText(MotherDashboardActivity.this, "Medical Reports & Ultrasound Scans", Toast.LENGTH_SHORT).show()
        );

        btnDoctorChat.setOnClickListener(v ->
                Toast.makeText(MotherDashboardActivity.this, "Chat with Assigned Doctor & Midwife", Toast.LENGTH_SHORT).show()
        );

        btnContractionTimer.setOnClickListener(v ->
                Toast.makeText(MotherDashboardActivity.this, "Contraction Timer Started ⏱️", Toast.LENGTH_SHORT).show()
        );

        btnNearbyHospitals.setOnClickListener(v ->
                Toast.makeText(MotherDashboardActivity.this, "Nearby Maternity Hospitals: Teaching Hospital & MOH Clinic", Toast.LENGTH_LONG).show()
        );

        btnEmergency.setOnClickListener(v ->
                Toast.makeText(MotherDashboardActivity.this, "🚨 EMERGENCY SOS ACTIVATED! Alerting Midwife & Emergency Contact...", Toast.LENGTH_LONG).show()
        );

        btnNotification.setOnClickListener(v ->
                Toast.makeText(MotherDashboardActivity.this, "No new emergency alerts. You're up to date!", Toast.LENGTH_SHORT).show()
        );

        // -----------------------------
        // Bottom Navigation
        // -----------------------------
        navHome.setOnClickListener(v -> {
            // Already on Home
        });

        navAppointments.setOnClickListener(v ->
                Toast.makeText(MotherDashboardActivity.this, "Clinic & Appointments", Toast.LENGTH_SHORT).show()
        );

        navHealth.setOnClickListener(v ->
                Toast.makeText(MotherDashboardActivity.this, "Health Tracker Details", Toast.LENGTH_SHORT).show()
        );

        navProfile.setOnClickListener(v ->
                Toast.makeText(MotherDashboardActivity.this, "Mother Profile Settings", Toast.LENGTH_SHORT).show()
        );
    }
}