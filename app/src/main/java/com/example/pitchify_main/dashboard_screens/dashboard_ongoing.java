package com.example.pitchify_main.dashboard_screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pitchify_main.R;

public class dashboard_ongoing extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_ongoing);

        TextView dashboard_ongoingpage_completionbutton = findViewById(R.id.dashboard_ongoing_completion);
        TextView dashboard_ongoingpage_overviewbutton = findViewById(R.id.dashboard_ongoing_Overview);
        ImageView dashboard_ongoingpage_editprofile = findViewById(R.id.dashboard_ongoing_profile);


        dashboard_ongoingpage_completionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard_ongoing.this, dashboard_completion.class);
                startActivity(intent);
            }
        });

        dashboard_ongoingpage_overviewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard_ongoing.this, dashboard_overview.class);
                startActivity(intent);
            }
        });

        dashboard_ongoingpage_editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard_ongoing.this, staff_profile.class);
                startActivity(intent);
            }

        });
    }
}