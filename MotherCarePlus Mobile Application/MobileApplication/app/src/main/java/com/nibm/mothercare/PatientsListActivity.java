package com.nibm.mothercare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PatientsListActivity extends AppCompatActivity {

    private ImageView btnBack;
    private Button btnVisitPatient1, btnRecordPatient1;
    private Button btnVisitPatient2, btnRecordPatient2;
    private Button btnVisitPatient3, btnRecordPatient3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_list);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        btnBack = findViewById(R.id.btnBack);
        btnVisitPatient1 = findViewById(R.id.btnVisitPatient1);
        btnRecordPatient1 = findViewById(R.id.btnRecordPatient1);

        btnVisitPatient2 = findViewById(R.id.btnVisitPatient2);
        btnRecordPatient2 = findViewById(R.id.btnRecordPatient2);

        btnVisitPatient3 = findViewById(R.id.btnVisitPatient3);
        btnRecordPatient3 = findViewById(R.id.btnRecordPatient3);

        btnBack.setOnClickListener(v -> finish());

        btnVisitPatient1.setOnClickListener(v -> openScheduleVisit("Kamala Perera"));
        btnRecordPatient1.setOnClickListener(v -> openHealthRecord("Kamala Perera"));

        btnVisitPatient2.setOnClickListener(v -> openScheduleVisit("Priya Fernando"));
        btnRecordPatient2.setOnClickListener(v -> openHealthRecord("Priya Fernando"));

        btnVisitPatient3.setOnClickListener(v -> openScheduleVisit("Nilmini Silva"));
        btnRecordPatient3.setOnClickListener(v -> openHealthRecord("Nilmini Silva"));
    }

    private void openScheduleVisit(String patientName) {
        Intent intent = new Intent(PatientsListActivity.this, ScheduleVisitActivity.class);
        intent.putExtra("selected_patient", patientName);
        startActivity(intent);
    }

    private void openHealthRecord(String patientName) {
        Intent intent = new Intent(PatientsListActivity.this, HealthRecordsActivity.class);
        intent.putExtra("selected_patient", patientName);
        startActivity(intent);
    }
}
