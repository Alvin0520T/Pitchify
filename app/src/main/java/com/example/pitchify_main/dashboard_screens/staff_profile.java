package com.example.pitchify_main.dashboard_screens;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pitchify_main.R;

public class staff_profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_profile);
        ImageButton staff_profile_backbutton = findViewById(R.id.staff_profile_backbutton);
        ImageView staff_profile_editbutton = findViewById(R.id.dashboard_profile_profile);
        ImageView profile_badges_badge3 = findViewById(R.id.profile_badges_badge3);

        staff_profile_backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(staff_profile.this, dashboard_overview.class);
                startActivity(intent);
            }
        });

        staff_profile_editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(staff_profile.this, staff_edit_profile.class);
                startActivity(intent);
            }
        });

        profile_badges_badge3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(staff_profile.this, badge_view.class);
                startActivity(intent);
            }
        });
    }

}

