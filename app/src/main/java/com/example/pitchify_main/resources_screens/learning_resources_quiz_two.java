package com.example.pitchify_main.resources_screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pitchify_main.R;

public class learning_resources_quiz_two extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning_resources_quizz_three);

        ImageView loginButton = findViewById(R.id.imageView28);

        // Set OnClickListener for the login button (ImageView)
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click, navigate to Login2Activity
                Intent intent = new Intent(learning_resources_quiz_two.this, learning_resources_quiz_three.class);
                startActivity(intent);
            }
        });
    }
}