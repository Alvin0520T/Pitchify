package com.example.pitchify_main.resource_screens;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.example.pitchify_main.R;

public class resources_training extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resources_training);


        ImageView loginButton = findViewById(R.id.filter_resourcess);

        // Set OnClickListener for the login button (ImageView)
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click, navigate to Login2Activity
                Intent intent = new Intent(resources_training.this, filter_resources.class);
                startActivity(intent);
            }
        });
    }
}



