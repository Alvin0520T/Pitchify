package com.example.pitchify_main.dashboard_screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pitchify_main.R;

public class badge_view extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badge_view);
        ImageButton badge_view_backbutton = findViewById(R.id.badge_view_backbutton);
        badge_view_backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(badge_view.this, staff_profile.class);
                startActivity(intent);
            }
        });
    }
}
