package com.example.pitchify_main.admin_staffoverviewperformance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pitchify_main.R;

public class staff_performance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_performance);

        ImageButton backButton = findViewById(R.id.backbutton);

        String userFirstName = getIntent().getStringExtra("USER_FIRST_NAME");
        TextView userNameTextView = findViewById(R.id.userNameTextView);
        userNameTextView.setText(userFirstName);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(staff_performance.this, admin_staffoverviewperformance_row.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
