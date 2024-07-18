package com.example.pitchify_main.dashboard_screens;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.example.pitchify_main.resource_screens.resources_training;
import com.example.pitchify_main.dashboard_screens.dashboard_overview;
import com.example.pitchify_main.aipitching_screens.aipitching_task;
import com.example.pitchify_main.dashboard_screens.staff_profile;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pitchify_main.R;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import org.w3c.dom.Text;


public class dashboard_overview extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.dashboard_overview);

        TextView dashboard_ongoingbutton = findViewById(R.id.dashboard_ongoing);
        TextView dashboard_completionbutton = findViewById(R.id.dashboard_completion);
        ImageView dashboard_profile_edit = findViewById(R.id.dashboard_profile);
        ImageView navigation1 = findViewById(R.id.nav_1);
        ImageView navigation2 = findViewById(R.id.nav_2);
        ImageView navigation3 = findViewById(R.id.nav_3);
        ImageView navigation4 = findViewById(R.id.nav_4);

        // this is for the ongoing button from overview page to ongoing //
        dashboard_ongoingbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard_overview.this, dashboard_ongoing.class);
                startActivity(intent);
            }
        });

        // this is for the completion button from overview page to completion //
        dashboard_completionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard_overview.this, dashboard_completion.class);
                startActivity(intent);
            }
        });

        dashboard_profile_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard_overview.this, staff_profile.class);
                startActivity(intent);
            }
        });

        navigation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard_overview.this, resources_training.class);
                startActivity(intent);
            }
        });

        navigation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard_overview.this, dashboard_overview.class);
                startActivity(intent);
            }
        });

        navigation3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard_overview.this, aipitching_task.class);
                startActivity(intent);
            }
        });

        navigation4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard_overview.this, staff_profile.class);
                startActivity(intent);
            }
        });

    }
}



