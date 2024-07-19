package com.example.pitchify_main.dashboard_screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pitchify_main.R;
import com.example.pitchify_main.login_screens.login;

public class onboarding5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding5);

        ImageButton backButton = findViewById(R.id.back_button);
        ImageButton startButton = findViewById(R.id.startJourneyButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(onboarding5.this, onboarding4.class);
                startActivity(intent);

            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(onboarding5.this, login.class);
                startActivity(intent);

            }
        });
    }
}


