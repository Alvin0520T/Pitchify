package com.example.pitchify_main.login_screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pitchify_main.R;
import com.example.pitchify_main.data.PitchifyDBHelper;

public class login3 extends AppCompatActivity {

    private EditText emailEditText;
    private PitchifyDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login3);

        dbHelper = new PitchifyDBHelper(this);
        emailEditText = findViewById(R.id.login3_enter_email);
        ImageView sentEmailButton = findViewById(R.id.login3_sent_email);
        ImageView backButton = findViewById(R.id.login3_arrow_back_button);

        sentEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleEmailSubmission();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login3.this, login2.class);
                startActivity(intent);
                finish(); // Optionally close the current activity
            }
        });
    }

    private void handleEmailSubmission() {
        String email = emailEditText.getText().toString().trim();

        // Validate that the email field is not empty
        if (email.isEmpty()) {
            Toast.makeText(this, "Email must not be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if the email exists in the database
        if (dbHelper.isEmailUsed(email)) {
            // If email is found, navigate to login5 activity
            navigateToLogin5();
        } else {
            Toast.makeText(this, "Email not found", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to navigate to login5 activity
    private void navigateToLogin5() {
        Intent intent = new Intent(login3.this, login5.class);
        startActivity(intent);
        finish(); // Optionally close the current activity
    }
}
