package com.example.pitchify_main.resource_screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pitchify_main.R;

public class filter_resources extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_resources_page);

        ImageView loginButton = findViewById(R.id.imageView6);


        // Set OnClickListener for the login button (ImageView)
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click, navigate to Login2Activity
                Intent intent = new Intent(filter_resources.this, resources_training.class);
                startActivity(intent);
            }

        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to resources_training
                Intent intent = new Intent(filter_resources.this, resources_training.class);
                startActivity(intent);
            }
        });
    }
}