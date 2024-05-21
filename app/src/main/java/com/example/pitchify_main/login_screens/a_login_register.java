package com.example.pitchify_main.login_screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.pitchify_main.R;

public class a_login_register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_login_register);


        ImageView loginButton = findViewById(R.id.admin_login_register_login_button);
        ImageView registerButton = findViewById(R.id.admin_login_register_register_button);
        ImageView backButton = findViewById(R.id.admin_login_register_arrow_back_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(a_login_register.this, a_signup.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(a_login_register.this, a_signup.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(a_login_register.this, login.class);
                startActivity(intent);
                finish(); // Optionally close the current activity
            }
        });
    }
}
