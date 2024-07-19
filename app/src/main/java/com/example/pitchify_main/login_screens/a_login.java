package com.example.pitchify_main.login_screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pitchify_main.R;
import com.example.pitchify_main.admin_staffoverviewperformance.admin_staffoverviewperformance_row;
import com.example.pitchify_main.dashboard_screens.dashboard_overview;
import com.example.pitchify_main.data.MPitchifyDBHelper;
import com.example.pitchify_main.model.User;


public class a_login extends AppCompatActivity {

    private MPitchifyDBHelper dbHelper;
    private EditText emailEditText;
    private EditText passwordEditText;
    private ImageView loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_login);

        dbHelper = new MPitchifyDBHelper(this);
        emailEditText = findViewById(R.id.admin_login_enter_email);
        passwordEditText = findViewById(R.id.admin_login_enter_password);
        loginButton = findViewById(R.id.admin_login_login_button);
        ImageView backButton = findViewById(R.id.admin_login_arrow_back_button);

        // Set up the login button click listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAdminLoginClick();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() { // New back button click listener
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(a_login.this, a_login_register.class);
                startActivity(intent);
                finish(); // Optionally close the current activity
            }
        });
    }



    private void onAdminLoginClick() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        User admin = dbHelper.getAdminByEmail(email);

        if (admin != null && password.equals(admin.getPassword())) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            // Proceed to the admin dashboard or next activity
            Intent intent = new Intent(a_login.this, admin_staffoverviewperformance_row.class);
            startActivity(intent);
            finish(); // Optionally, finish this activity to prevent going back to it from the admin_profile
        } else {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
    }
}
