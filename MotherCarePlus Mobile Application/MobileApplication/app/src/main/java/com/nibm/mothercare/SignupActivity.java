package com.nibm.mothercare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SignupActivity extends AppCompatActivity {

    TextInputEditText edtName;
    TextInputEditText edtEmail;
    TextInputEditText edtPassword;

    MaterialButton btnSignUp;

    TextView txtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        btnSignUp = findViewById(R.id.btnSignUp);

        txtLogin = findViewById(R.id.txtLogin);

        btnSignUp.setOnClickListener(v -> {

            String name = edtName.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();


            if (name.isEmpty()) {
                edtName.setError("Please enter your name");
                edtName.requestFocus();
                return;
            }


            if (email.isEmpty()) {
                edtEmail.setError("Please enter your email");
                edtEmail.requestFocus();
                return;
            }


            if (password.isEmpty()) {
                edtPassword.setError("Please create a password");
                edtPassword.requestFocus();
                return;
            }


            if (password.length() < 6) {
                edtPassword.setError(
                        "Password must contain at least 6 characters"
                );
                edtPassword.requestFocus();
                return;
            }

            Intent intent = new Intent(
                    SignupActivity.this,
                    GetStartedActivity.class
            );

            startActivity(intent);
            finish();
        });

        txtLogin.setOnClickListener(v -> {

            Intent intent = new Intent(
                    SignupActivity.this,
                    LoginActivity.class
            );

            startActivity(intent);

            finish();
        });
    }
}