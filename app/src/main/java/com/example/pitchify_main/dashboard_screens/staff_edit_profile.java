package com.example.pitchify_main.dashboard_screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pitchify_main.R;
import com.example.pitchify_main.login_screens.login;

public class staff_edit_profile  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_edit_profile);

        ImageButton staff_edit_profile_backbutton = findViewById(R.id.staff_edit_profile_backbutton);
        TextView logOutButton = findViewById(R.id.dashboard_on_progress_log_out_1);
        staff_edit_profile_backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(staff_edit_profile.this, staff_profile.class);
                startActivity(intent);
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(staff_edit_profile.this, login.class);
                startActivity(intent);
            }
        });
    }
}