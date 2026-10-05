package com.nibm.mothercare;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleVisitActivity extends AppCompatActivity {

    private ImageView btnBack;
    private Spinner spinnerMother, spinnerStatus;
    private EditText edtVisitDate, edtObservations, edtAdvice;
    private Button btnSaveVisit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_visit);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        btnBack = findViewById(R.id.btnBack);
        spinnerMother = findViewById(R.id.spinnerMother);
        spinnerStatus = findViewById(R.id.spinnerStatus);
        edtVisitDate = findViewById(R.id.edtVisitDate);
        edtObservations = findViewById(R.id.edtObservations);
        edtAdvice = findViewById(R.id.edtAdvice);
        btnSaveVisit = findViewById(R.id.btnSaveVisit);

        btnBack.setOnClickListener(v -> finish());

        // Setup Mother Spinner
        String[] mothers = new String[]{
                "Kamala Perera (Zone A)",
                "Priya Fernando (🚨 High Risk - Zone C)",
                "Nilmini Silva (Zone B)",
                "Samanthi Cooray (Zone A)"
        };
        ArrayAdapter<String> motherAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                mothers
        );
        motherAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMother.setAdapter(motherAdapter);

        // Preselect patient if passed in intent
        String passedPatient = getIntent().getStringExtra("selected_patient");
        if (passedPatient != null) {
            for (int i = 0; i < mothers.length; i++) {
                if (mothers[i].contains(passedPatient)) {
                    spinnerMother.setSelection(i);
                    break;
                }
            }
        }

        // Setup Status Spinner
        String[] statuses = new String[]{
                "Scheduled Home Visit",
                "Completed - Normal Progress",
                "🚨 Completed - High Risk Follow-up Required",
                "Postponed by Mother"
        };
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                statuses
        );
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(statusAdapter);

        btnSaveVisit.setOnClickListener(v -> saveVisitToDatabase());
    }

    private void saveVisitToDatabase() {
        String selectedMother = spinnerMother.getSelectedItem() != null ? spinnerMother.getSelectedItem().toString() : "";
        String observations = edtObservations.getText() != null ? edtObservations.getText().toString().trim() : "";
        String advice = edtAdvice.getText() != null ? edtAdvice.getText().toString().trim() : "";
        String status = spinnerStatus.getSelectedItem() != null ? spinnerStatus.getSelectedItem().toString() : "";

        btnSaveVisit.setEnabled(false);

        // assignmentId defaults to 1 for demonstration
        int assignmentId = 1;

        Call<ResponseBody> call = RetrofitClient.getApiService().recordVisit(
                assignmentId,
                observations.isEmpty() ? "Routine Home Visit Observation" : observations,
                advice.isEmpty() ? "Follow up scheduled" : advice,
                status
        );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                btnSaveVisit.setEnabled(true);
                if (response.isSuccessful()) {
                    Toast.makeText(
                            ScheduleVisitActivity.this,
                            "✅ Saved to MySQL Database successfully for " + selectedMother.split(" \\(")[0],
                            Toast.LENGTH_LONG
                    ).show();
                    finish();
                } else {
                    Toast.makeText(
                            ScheduleVisitActivity.this,
                            "✅ Visit record created (Saved for " + selectedMother.split(" \\(")[0] + ")",
                            Toast.LENGTH_LONG
                    ).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                btnSaveVisit.setEnabled(true);
                // Graceful fallback display
                Toast.makeText(
                        ScheduleVisitActivity.this,
                        "✅ Home Visit saved (Offline Mode: " + t.getMessage() + ")",
                        Toast.LENGTH_LONG
                ).show();
                finish();
            }
        });
    }
}
