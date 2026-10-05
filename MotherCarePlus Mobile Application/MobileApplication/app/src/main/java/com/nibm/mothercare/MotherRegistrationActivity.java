package com.nibm.mothercare;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MotherRegistrationActivity extends AppCompatActivity {

    private TextInputEditText edtName;
    private TextInputEditText edtEmail;
    private TextInputEditText edtPassword;
    private TextInputEditText edtPhone;
    private TextInputEditText edtAddress;
    private TextInputEditText edtDateOfBirth;
    private TextInputLayout   layoutDateOfBirth;

    private MaterialButton btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mother_registration);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        edtName           = findViewById(R.id.edtMotherName);
        edtEmail          = findViewById(R.id.edtMotherEmail);
        edtPassword       = findViewById(R.id.edtMotherPassword);
        edtPhone          = findViewById(R.id.edtMotherPhone);
        edtAddress        = findViewById(R.id.edtMotherAddress);
        edtDateOfBirth    = findViewById(R.id.edtMotherDateOfBirth);
        layoutDateOfBirth = findViewById(R.id.layoutMotherDateOfBirth);

        btnRegister = findViewById(R.id.btnRegisterMother);

        android.widget.ImageView btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // Open DatePicker when field or calendar icon is tapped
        edtDateOfBirth.setOnClickListener(v -> openDatePicker());
        if (layoutDateOfBirth != null) {
            layoutDateOfBirth.setEndIconOnClickListener(v -> openDatePicker());
        }

        btnRegister.setOnClickListener(v -> registerMother());
    }

    /** Opens a styled DatePickerDialog and fills the date field on selection. */
    private void openDatePicker() {
        Calendar cal  = Calendar.getInstance();
        int year  = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day   = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                this,
                R.style.DatePickerTheme,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String date = String.format(
                            Locale.getDefault(),
                            "%04d-%02d-%02d",
                            selectedYear,
                            selectedMonth + 1,
                            selectedDay
                    );
                    edtDateOfBirth.setText(date);
                },
                year, month, day
        );

        // Mother must be at least 15 years old
        Calendar maxDate = Calendar.getInstance();
        maxDate.add(Calendar.YEAR, -15);
        dialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());

        dialog.show();
    }

    private void registerMother() {
        String name        = getText(edtName);
        String email       = getText(edtEmail);
        String password    = getText(edtPassword);
        String phone       = getText(edtPhone);
        String address     = getText(edtAddress);
        String dateOfBirth = getText(edtDateOfBirth);

        if (TextUtils.isEmpty(name)) {
            edtName.setError("Please enter mother's name");
            edtName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            edtEmail.setError("Please enter mother's email");
            edtEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            edtPassword.setError("Please enter password");
            edtPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            edtPassword.setError("Password must contain at least 6 characters");
            edtPassword.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(dateOfBirth)) {
            edtDateOfBirth.setError("Please select date of birth");
            openDatePicker();
            return;
        }

        btnRegister.setEnabled(false);
        btnRegister.setText("Registering...");

        Call<PregnantMother> call =
                RetrofitClient.getApiService().registerMother(
                        name, email, password, phone, address, dateOfBirth
                );

        call.enqueue(new Callback<PregnantMother>() {
            @Override
            public void onResponse(
                    Call<PregnantMother> call,
                    Response<PregnantMother> response) {

                btnRegister.setEnabled(true);
                btnRegister.setText("Register Mother  \u2192");

                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(
                            MotherRegistrationActivity.this,
                            "Mother registered successfully \u2713",
                            Toast.LENGTH_LONG
                    ).show();
                    finish();
                } else {
                    String message = "Registration failed";
                    if (response.errorBody() != null) {
                        try { message = response.errorBody().string(); }
                        catch (Exception ignored) {}
                    }
                    Toast.makeText(
                            MotherRegistrationActivity.this,
                            message,
                            Toast.LENGTH_LONG
                    ).show();
                }
            }

            @Override
            public void onFailure(Call<PregnantMother> call, Throwable t) {
                btnRegister.setEnabled(true);
                btnRegister.setText("Register Mother  \u2192");
                Toast.makeText(
                        MotherRegistrationActivity.this,
                        "Connection failed: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }

    private String getText(TextInputEditText editText) {
        if (editText.getText() == null) return "";
        return editText.getText().toString().trim();
    }
}