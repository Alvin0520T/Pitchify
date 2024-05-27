package com.example.pitchify_main.login_screens;
import com.example.pitchify_main.dashboard_screens.dashboard_overview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pitchify_main.R;
import com.example.pitchify_main.data.PitchifyDBHelper;
import com.example.pitchify_main.model.User;




public class login2 extends AppCompatActivity {

    private PitchifyDBHelper dbHelper;
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login2);

        dbHelper = new PitchifyDBHelper(this);
        emailEditText = findViewById(R.id.enter_email);
        passwordEditText = findViewById(R.id.enter_password);
        ImageView loginButton = findViewById(R.id.login2_button);
        ImageView backButton = findViewById(R.id.arrow_back_button);
        ImageView forgetPasswordButton = findViewById(R.id.login2_forget_password);


        try {
            // Initialize the database with initial user data
            boolean initialized = dbHelper.initializeVendorData();
            if (!initialized) {
                // Handle initialization failure if needed
                Toast.makeText(this, "Failed to initialize database with initial user data", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace(); // or handle the exception in another way
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() { // New back button click listener
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login2.this, login.class);
                startActivity(intent);
                finish(); // Optionally close the current activity
            }
        });

        forgetPasswordButton.setOnClickListener(new View.OnClickListener() { // New forget password button click listener
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login2.this, login3.class);
                startActivity(intent);
                finish(); // Optionally close the current activity
            }
        });

    }



    private void handleLogin() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validate that the email and password fields are not empty
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and Password must not be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = dbHelper.authenticateUser(email, password);

        // Check if the user exists and the credentials are correct
        if (user != null) {
            Intent intent = new Intent(login2.this, dashboard_overview.class);
            startActivity(intent);
            finish(); // Optionally close the login activity
        } else {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
    }
}

