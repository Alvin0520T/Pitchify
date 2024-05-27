package com.example.pitchify_main.login_screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pitchify_main.R;
import com.example.pitchify_main.data.MPitchifyDBHelper;
import com.example.pitchify_main.model.User;

public class a_register extends AppCompatActivity {

    private MPitchifyDBHelper dbHelper;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_register);

        dbHelper = new MPitchifyDBHelper(this);
        firstNameEditText = findViewById(R.id.admin_register_enter_first_name);
        lastNameEditText = findViewById(R.id.admin_register_enter_last_name);
        emailEditText = findViewById(R.id.admin_register_enter_email);
        passwordEditText = findViewById(R.id.admin_register_enter_password);
        ImageView registerButton = findViewById(R.id.admin_register_create_account);

        ImageView backButton = findViewById(R.id.admin_register_arrow_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(a_register.this, a_login_register.class);
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
            Intent intent = new Intent(a_register.this, a_register_success.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
    }
}
