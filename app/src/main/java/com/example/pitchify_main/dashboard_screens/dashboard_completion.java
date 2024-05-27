package com.example.pitchify_main.dashboard_screens;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pitchify_main.R;

public class dashboard_completion extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_completion);

        TextView dashboard_completion = findViewById(R.id.dashboard_ongoing_Overview);
        TextView dashboard_ongoing = findViewById(R.id.dashboard_ongoing_ongoing);
        ImageView dashboard_completion_editprofile = findViewById(R.id.dashboard_ongoing_profile);

        dashboard_completion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard_completion.this, dashboard_overview.class);
                startActivity(intent);
            }
        });

        dashboard_ongoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard_completion.this, dashboard_ongoing.class);
                startActivity(intent);
            }
        });

        dashboard_completion_editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard_completion.this, staff_profile.class);
                startActivity(intent);
            }
        });
    }
}

