package com.example.pitchify_main.data;

import com.example.pitchify_main.model.User;

import java.util.ArrayList;

public class ManagerInitData {

    public static ArrayList<User> initializeToArrayList() {
        ArrayList<User> initialUserList = new ArrayList<>();

        // Create an admin user with predefined credentials
        User admin = new User("AdminFirstName", "AdminLastName", "admin@gmail.com", "admin123");

        // Add the admin user to the list
        initialUserList.add(admin);

        return initialUserList;
    }
}
