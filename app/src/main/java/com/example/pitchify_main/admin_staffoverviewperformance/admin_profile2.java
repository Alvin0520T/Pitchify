package com.example.pitchify_main.admin_staffoverviewperformance;

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

public class admin_profile2 extends AppCompatActivity {

    private PitchifyDBHelper dbHelper;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_profile2);

        dbHelper = new PitchifyDBHelper(this);
        firstNameEditText = findViewById(R.id.admin_register_enter_first_name);
        lastNameEditText = findViewById(R.id.admin_register_enter_last_name);
        emailEditText = findViewById(R.id.admin_register_enter_email);
        passwordEditText = findViewById(R.id.admin_register_enter_password);
        ImageView registerButton = findViewById(R.id.admin_register_create_account);

        ImageView backButton = findViewById(R.id.admin_register_arrow_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_profile2.this, admin_staffoverviewperformance_row.class);
                startActivity(intent);
                finish();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (dbHelper.isEmailUsed(email)) {
            Toast.makeText(this, "Email is already in use", Toast.LENGTH_SHORT).show();
            return;
        }

        User newUser = new User(firstName, lastName, email, password);
        boolean isInserted = dbHelper.addNewVendor(newUser);

        if (isInserted) {
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(admin_profile2.this, admin_staffoverviewperformance_row.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
    }
}
