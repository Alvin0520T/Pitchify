package com.example.pitchify_main.dashboard_screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pitchify_main.R;

public class onboarding4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onbaording4);

        ImageButton nextButton = findViewById(R.id.next_button);
        ImageButton backButton = findViewById(R.id.back_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(onboarding4.this, onboarding5.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(onboarding4.this, onboarding3.class);
                startActivity(intent);

            }
        });
    }
}