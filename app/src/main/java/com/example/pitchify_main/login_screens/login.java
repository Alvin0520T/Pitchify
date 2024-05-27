package com.example.pitchify_main.login_screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pitchify_main.R;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ImageView loginButton = findViewById(R.id.login_button);
        ImageView retailManagerButton = findViewById(R.id.ru_retail_manager); // New button

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, login2.class);
                startActivity(intent);
            }
        });

        retailManagerButton.setOnClickListener(new View.OnClickListener() { // New button click listener
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, a_login_register.class);
                startActivity(intent);
            }
        });
    }
}
