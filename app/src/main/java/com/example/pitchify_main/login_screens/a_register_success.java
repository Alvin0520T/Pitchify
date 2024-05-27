package com.example.pitchify_main.login_screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pitchify_main.R;

public class a_register_success extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_register_success);

        ImageView backToAdminLoginButton = findViewById(R.id.admin_register_back_to_admin_login_button);
        ImageView backToUserLoginButton = findViewById(R.id.admin_register_back_to_user_login_button);
        ImageView createAnotherAccountButton = findViewById(R.id.admin_register_create_another_account);

        backToAdminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(a_register_success.this, a_login_register.class);
                startActivity(intent);
                finish(); // Optionally close the current activity
            }
        });

        backToUserLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(a_register_success.this, login.class);
                startActivity(intent);
                finish(); // Optionally close the current activity
            }
        });

        createAnotherAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(a_register_success.this, a_register.class);
                startActivity(intent);
                finish(); // Optionally close the current activity
            }
        });
    }
}
