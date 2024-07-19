package com.example.pitchify_main.aipitching_screens;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pitchify_main.R;
import com.example.pitchify_main.dashboard_screens.dashboard_overview;
import com.example.pitchify_main.dashboard_screens.staff_profile;
import com.example.pitchify_main.resource_screens.resources_training;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class aipitching_task extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aipitching_task);



        ImageView startbutton = findViewById(R.id.startbutton);




        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(aipitching_task.this, aipitching_training.class);
                startActivity(intent);
            }
        });

    }
}