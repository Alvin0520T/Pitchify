package com.example.pitchify_main.resource_screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pitchify_main.ChatActivity;
import com.example.pitchify_main.R;

public class resources_enroll_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resources_enroll_page);

        ImageView loginButton = findViewById(R.id.imageView19);
        ImageView backButton = findViewById(R.id.imageView13);


        // Set OnClickListener for the login button (ImageView)
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click, navigate to Login2Activity
                Intent intent = new Intent(resources_enroll_page.this, ChatActivity.class);
                startActivity(intent);
            }

        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to resources_training
                Intent intent = new Intent(resources_enroll_page.this, resources_training.class);
                startActivity(intent);
            }
        });
    }
}

