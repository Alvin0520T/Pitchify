package com.example.pitchify_main.dashboard_screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pitchify_main.R;

public class onboarding1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding1);

        // Create a Handler to delay the intent for 3 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the onboarding2 activity
                Intent intent = new Intent(onboarding1.this, onboarding2.class);
                startActivity(intent);
                // Finish the current activity if you don't want the user to return to it
                finish();
            }
        }, 3000); // 3000 milliseconds = 3 seconds
    }
}