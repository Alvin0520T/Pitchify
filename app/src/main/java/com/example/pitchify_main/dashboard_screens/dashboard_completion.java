package com.example.pitchify_main.dashboard_screens;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pitchify_main.R;
import com.example.pitchify_main.aipitching_screens.aipitching_task;
import com.example.pitchify_main.resource_screens.resources_training;

public class dashboard_completion extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_completion);

        TextView dashboard_completion = findViewById(R.id.dashboard_ongoing_Overview);
        TextView dashboard_ongoing = findViewById(R.id.dashboard_ongoing_ongoing);
        ImageView dashboard_completion_editprofile = findViewById(R.id.dashboard_ongoing_profile);
        ImageView navigation1 = findViewById(R.id.nav_1);
        ImageView navigation2 = findViewById(R.id.nav_2);
        ImageView navigation3 = findViewById(R.id.nav_3);
        ImageView navigation4 = findViewById(R.id.nav_4);

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

        navigation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard_completion.this, resources_training.class);
                startActivity(intent);
            }
        });

        navigation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard_completion.this, dashboard_overview.class);
                startActivity(intent);
            }
        });

        navigation3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard_completion.this, aipitching_task.class);
                startActivity(intent);
            }
        });

        navigation4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard_completion.this, staff_profile.class);
                startActivity(intent);
            }
        });
    }
}

