package com.example.pitchify_main.login_screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pitchify_main.R;

public class login5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login5);

        ImageView doLaterButton = findViewById(R.id.login5_do_later);

        doLaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the login activity
                Intent intent = new Intent(login5.this, login.class);
                startActivity(intent);
                finish(); // Optionally close the current activity
            }
        });
    }
}
