package com.example.pitchify_main.dashboard_screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.pitchify_main.R;


public class dashboard_overview extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.dashboard_overview);
        TextView dashboard_ongoingbutton = findViewById(R.id.dashboard_ongoing);

        dashboard_ongoingbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                Intent intent = new Intent(dashboard_overview.this, dashboard_ongoing.class);
                startActivity(intent);
        }
});
    }
    }
