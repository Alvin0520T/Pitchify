package com.example.pitchify_main.dashboard_screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pitchify_main.R;

public class staff_edit_profile  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_edit_profile);

        ImageButton staff_edit_profile_backbutton = findViewById(R.id.staff_edit_profile_backbutton);
        staff_edit_profile_backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(staff_edit_profile.this, staff_profile.class);
                startActivity(intent);
            }
        });
    }
}