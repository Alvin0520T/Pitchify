package com.example.pitchify_main.resource_screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pitchify_main.ChatActivity;
import com.example.pitchify_main.R;
import com.example.pitchify_main.aipitching_screens.aipitching_task;
import com.example.pitchify_main.dashboard_screens.dashboard_overview;
import com.example.pitchify_main.dashboard_screens.staff_profile;

public class resources_training extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resources_training);

        ImageView loginButton = findViewById(R.id.filter_resourcess);
        ImageView chatButton = findViewById(R.id.imageView2);
        ImageView navigation1 = findViewById(R.id.nav_1);
        ImageView navigation2 = findViewById(R.id.nav_2);
        ImageView navigation3 = findViewById(R.id.nav_3);
        ImageView navigation4 = findViewById(R.id.nav_4);

        // Set OnClickListener for the login button (ImageView)
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click, navigate to filter_resources
                Intent intent = new Intent(resources_training.this, filter_resources.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for the chat button (ImageView)
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click, navigate to ChatActivity
                Intent intent = new Intent(resources_training.this, resources_enroll_page.class);
                startActivity(intent);
            }
        });

        navigation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(resources_training.this, resources_training.class);
                startActivity(intent);
            }
        });

        navigation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(resources_training.this, dashboard_overview.class);
                startActivity(intent);
            }
        });

        navigation3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(resources_training.this, aipitching_task.class);
                startActivity(intent);
            }
        });

        navigation4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(resources_training.this, staff_profile.class);
                startActivity(intent);
            }
        });
    }
}
