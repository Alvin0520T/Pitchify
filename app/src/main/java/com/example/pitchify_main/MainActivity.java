package com.example.pitchify_main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;


import com.example.pitchify_main.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning_resources_starting_page);
        setContentView(R.layout.login);

        ImageView learningButton = findViewById(R.id.learning_button12);
        learningButton.setOnClickListener(v -> {
            // Navigate to ChatActivity
            Intent intent = new Intent(MainActivity.this, ChatActivity.class);
            startActivity(intent);
        });
    }


}

