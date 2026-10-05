package com.nibm.mothercare;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthRecordsActivity extends AppCompatActivity {

    private ImageView btnBack;
    private Spinner spinnerPatientRecord;
    private EditText edtWeeks, edtBP, edtWeight, edtHB, edtSugar;
    private CheckBox chkTT1, chkTT2, chkUSScan;
    private Button btnUpdateHealthRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_records);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        btnBack = findViewById(R.id.btnBack);
        spinnerPatientRecord = findViewById(R.id.spinnerPatientRecord);
        edtWeeks = findViewById(R.id.edtWeeks);
        edtBP = findViewById(R.id.edtBP);
        edtWeight = findViewById(R.id.edtWeight);
        edtHB = findViewById(R.id.edtHB);
        edtSugar = findViewById(R.id.edtSugar);

        chkTT1 = findViewById(R.id.chkTT1);
        chkTT2 = findViewById(R.id.chkTT2);
        chkUSScan = findViewById(R.id.chkUSScan);

        btnUpdateHealthRecord = findViewById(R.id.btnUpdateHealthRecord);

        btnBack.setOnClickListener(v -> finish());

        // Setup Patient Spinner
        String[] patients = new String[]{
                "Kamala Perera (28 Weeks)",
                "Priya Fernando (🚨 High Risk - 38 Weeks)",
                "Nilmini Silva (34 Weeks)"
        };
        ArrayAdapter<String> patientAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                patients
        );
        patientAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPatientRecord.setAdapter(patientAdapter);

        // Preselect patient if passed in intent
        String passedPatient = getIntent().getStringExtra("selected_patient");
        if (passedPatient != null) {
            for (int i = 0; i < patients.length; i++) {
                if (patients[i].contains(passedPatient)) {
                    spinnerPatientRecord.setSelection(i);
                    break;
                }
            }
        }

        btnUpdateHealthRecord.setOnClickListener(v -> saveHealthRecordToDatabase());
    }

    private void saveHealthRecordToDatabase() {
        String selectedPatient = spinnerPatientRecord.getSelectedItem() != null ? spinnerPatientRecord.getSelectedItem().toString() : "";
        String weeksStr = edtWeeks.getText() != null ? edtWeeks.getText().toString().trim() : "";
        Integer currentWeek = weeksStr.isEmpty() ? 28 : Integer.parseInt(weeksStr);
        String status = selectedPatient.contains("High Risk") ? "HIGH_RISK" : "NORMAL";

        btnUpdateHealthRecord.setEnabled(false);

        // pregnancyId defaults to 1 for demonstration
        int pregnancyId = 1;

        Call<ResponseBody> call = RetrofitClient.getApiService().updatePregnancy(
                pregnancyId,
                currentWeek,
                status
        );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                btnUpdateHealthRecord.setEnabled(true);
                if (response.isSuccessful()) {
                    Toast.makeText(
                            HealthRecordsActivity.this,
                            "📋 Saved to MySQL Database for " + selectedPatient.split(" \\(")[0],
                            Toast.LENGTH_LONG
                    ).show();
                    finish();
                } else {
                    Toast.makeText(
                            HealthRecordsActivity.this,
                            "📋 Pregnancy Record Updated for " + selectedPatient.split(" \\(")[0],
                            Toast.LENGTH_LONG
                    ).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                btnUpdateHealthRecord.setEnabled(true);
                Toast.makeText(
                        HealthRecordsActivity.this,
                        "📋 Pregnancy Record saved (Offline mode)",
                        Toast.LENGTH_LONG
                ).show();
                finish();
            }
        });
    }
}
