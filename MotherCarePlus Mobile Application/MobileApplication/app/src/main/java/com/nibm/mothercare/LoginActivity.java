package com.nibm.mothercare;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText edtEmail;
    private TextInputEditText edtPassword;
    private MaterialButton btnLogin;
    private TextView txtSignUp;
    private TextView txtForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtSignUp = findViewById(R.id.txtSignUp);
        txtForgotPassword = findViewById(R.id.txtForgotPassword);

        btnLogin.setOnClickListener(v -> loginUser());

        // Mother accounts are created by Midwife/MOH, so public signup is disabled.
        txtSignUp.setOnClickListener(v -> Toast.makeText(
                LoginActivity.this,
                "Mother registration is done by Midwife/MOH",
                Toast.LENGTH_LONG
        ).show());

        txtForgotPassword.setOnClickListener(v -> Toast.makeText(
                LoginActivity.this,
                "Forgot password feature coming soon",
                Toast.LENGTH_SHORT
        ).show());
    }

    private void loginUser() {
        String email = edtEmail.getText() == null
                ? ""
                : edtEmail.getText().toString().trim();
        String password = edtPassword.getText() == null
                ? ""
                : edtPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            edtEmail.setError("Please enter your email");
            edtEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            edtPassword.setError("Please enter your password");
            edtPassword.requestFocus();
            return;
        }

        btnLogin.setEnabled(false);

        LoginRequest request = new LoginRequest(email, password);
        Call<LoginResponse> call = RetrofitClient.getApiService().login(request);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                btnLogin.setEnabled(true);

                if (response.isSuccessful() && response.body() != null) {

                    LoginResponse result = response.body();

                    if (result.isSuccess() && result.getUser() != null) {

                        User user = result.getUser();

                        Toast.makeText(
                                LoginActivity.this,
                                "Welcome " + user.getName(),
                                Toast.LENGTH_SHORT
                        ).show();

                        openDashboard(user);

                    } else {

                        String message = result.getMessage();

                        if (message == null || message.isEmpty()) {
                            message = "Login failed";
                        }

                        Toast.makeText(
                                LoginActivity.this,
                                message,
                                Toast.LENGTH_LONG
                        ).show();
                    }

                } else {

                    String message = "Server error: HTTP " + response.code();

                    try {
                        if (response.errorBody() != null) {

                            String errorJson = response.errorBody().string();

                            LoginResponse errorResponse =
                                    new com.google.gson.Gson().fromJson(
                                            errorJson,
                                            LoginResponse.class
                                    );

                            if (errorResponse != null
                                    && errorResponse.getMessage() != null
                                    && !errorResponse.getMessage().isEmpty()) {

                                message = errorResponse.getMessage();
                            }
                        }
                    } catch (Exception e) {
                        message = "Server error: HTTP " + response.code();
                    }

                    Toast.makeText(
                            LoginActivity.this,
                            message,
                            Toast.LENGTH_LONG
                    ).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                btnLogin.setEnabled(true);
                Toast.makeText(
                        LoginActivity.this,
                        "Connection failed: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }

    private void openDashboard(User user) {

        Intent intent;

        switch (user.getRole()) {

            case "MOTHER":
                intent = new Intent(
                        LoginActivity.this,
                        MotherDashboardActivity.class
                );
                break;

            case "MIDWIFE":
                intent = new Intent(
                        LoginActivity.this,
                        MidwifeDashboardActivity.class
                );
                break;

            case "DOCTOR":
                Toast.makeText(
                        LoginActivity.this,
                        "Doctor dashboard coming soon",
                        Toast.LENGTH_SHORT
                ).show();
                return;

            case "ADMIN":
                Toast.makeText(
                        LoginActivity.this,
                        "Admin dashboard coming soon",
                        Toast.LENGTH_SHORT
                ).show();
                return;

            default:
                Toast.makeText(
                        LoginActivity.this,
                        "Unknown user role",
                        Toast.LENGTH_SHORT
                ).show();
                return;
        }

        intent.putExtra("user_id", user.getUserId());
        intent.putExtra("name", user.getName());
        intent.putExtra("email", user.getEmail());
        intent.putExtra("role", user.getRole());

        startActivity(intent);
        finish();
    }
}
