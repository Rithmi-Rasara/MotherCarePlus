package com.nibm.mothercare;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class GetStartedActivity extends AppCompatActivity {

    MaterialButton btnGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_get_started);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        btnGetStarted = findViewById(R.id.btnGetStarted);

        btnGetStarted.setOnClickListener(v -> {

            Intent intent = new Intent(
                    GetStartedActivity.this,
                    LoginActivity.class
            );

            startActivity(intent);
        });
    }
}