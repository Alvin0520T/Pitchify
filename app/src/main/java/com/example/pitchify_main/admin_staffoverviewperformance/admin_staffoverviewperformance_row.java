package com.example.pitchify_main.admin_staffoverviewperformance;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pitchify_main.R;
import com.example.pitchify_main.dashboard_screens.onboarding3;
import com.example.pitchify_main.dashboard_screens.onboarding4;
import com.example.pitchify_main.data.CustomAdapter;
import com.example.pitchify_main.data.PitchifyDBHelper;
import com.example.pitchify_main.model.User;
import java.util.ArrayList;

public class admin_staffoverviewperformance_row extends AppCompatActivity {

    RecyclerView recyclerView;
    PitchifyDBHelper pitchifyDBHelper;
    ArrayList<String> firstname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_staffoverviewperformance_row);
        ImageButton nextButton = findViewById(R.id.imageButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_staffoverviewperformance_row.this, admin_profile2.class);
                startActivity(intent);

            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        pitchifyDBHelper = new PitchifyDBHelper(admin_staffoverviewperformance_row.this);
        firstname = new ArrayList<>();

        try {
            pitchifyDBHelper.initializeVendorData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        fetchUsersFromDatabase();

        CustomAdapter customAdapter = new CustomAdapter(this, firstname);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fetchUsersFromDatabase() {
        ArrayList<User> users = pitchifyDBHelper.getAllUsers();
        for (User user : users) {
            firstname.add(user.getFirstName());
            Log.d("FetchUsers", "Fetched user: " + user.getFirstName());
        }
        Log.d("FetchUsers", "Total users fetched: " + firstname.size());
    }
}