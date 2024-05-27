package com.example.pitchify_main.aipitching_screens;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pitchify_main.R;

public class aipitching_permission extends AppCompatActivity {
    ImageButton aipitching_permission_backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aipitching_permission);

        aipitching_permission_backbutton = findViewById(R.id.aipitching_permission_backbutton);
        aipitching_permission_backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


}
}