package com.nibm.mothercare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MidwifeProfileActivity extends AppCompatActivity {

    private ImageView btnBack;
    private TextView txtMidwifeFullName, txtMohDivision;
    private TextView txtStatPatients, txtStatHighRisk;
    private TextView btnEditProfile, btnSyncData, btnMOHHotline, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midwife_profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        btnBack = findViewById(R.id.btnBack);
        txtMidwifeFullName = findViewById(R.id.txtMidwifeFullName);
        txtMohDivision = findViewById(R.id.txtMohDivision);
        txtStatPatients = findViewById(R.id.txtStatPatients);
        txtStatHighRisk = findViewById(R.id.txtStatHighRisk);

        btnEditProfile = findViewById(R.id.btnEditProfile);
        btnSyncData = findViewById(R.id.btnSyncData);
        btnMOHHotline = findViewById(R.id.btnMOHHotline);
        btnLogout = findViewById(R.id.btnLogout);

        btnBack.setOnClickListener(v -> finish());

        String midwifeName = getIntent().getStringExtra("name");
        if (midwifeName != null && !midwifeName.trim().isEmpty()) {
            txtMidwifeFullName.setText(midwifeName);
        }

        btnEditProfile.setOnClickListener(v ->
                Toast.makeText(MidwifeProfileActivity.this, "Edit Profile details opened", Toast.LENGTH_SHORT).show()
        );

        btnSyncData.setOnClickListener(v ->
                Toast.makeText(MidwifeProfileActivity.this, "🔄 Field Records synced with MOH Server successfully!", Toast.LENGTH_SHORT).show()
        );

        btnMOHHotline.setOnClickListener(v ->
                Toast.makeText(MidwifeProfileActivity.this, "Calling 1990 Emergency MOH Hotline...", Toast.LENGTH_SHORT).show()
        );

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(MidwifeProfileActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
