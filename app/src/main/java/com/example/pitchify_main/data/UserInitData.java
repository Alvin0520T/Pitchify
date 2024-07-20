package com.example.pitchify_main.data;

import com.example.pitchify_main.model.User;

import java.util.ArrayList;

public class UserInitData {

    public static ArrayList<User> initializeUserToArrayList() throws Exception {
        ArrayList<User> initialUserList = new ArrayList<>();

        User user1 = new User("Mariam", "teh", "mariam@gmail.com", "mhteh");
        User user2 = new User("Zaman", "tang", "zaman@gmail.com", "alvintang");
        User user3 = new User("Ming Heng", "teh", "mhteh@gmail.com", "mhteh");
        User user4 = new User("Alvin Tang", "tang", "alvintang@gmail.com", "alvintang");
        User user5 = new User("Pei Jun", "lee", "pjlee@gmail.com", "pjlee");
        User user6 = new User("Yuen Sheun", "lau", "yslau@gmail.com", "yslau");
        User user7 = new User("Yi Harn", "lim", "yhlim@gmail.com", "yhlim");


        initialUserList.add(user1);
        initialUserList.add(user2);
        initialUserList.add(user3);
        initialUserList.add(user4);
        initialUserList.add(user5);
        initialUserList.add(user6);
        initialUserList.add(user7);


        return initialUserList;
    }
}
