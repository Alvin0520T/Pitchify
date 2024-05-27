package com.example.pitchify_main.data;

import com.example.pitchify_main.model.User;

import java.util.ArrayList;

public class UserInitData {

    public static ArrayList<User> initializeUserToArrayList() throws Exception {
        ArrayList<User> initialUserList = new ArrayList<>();

        User user1 = new User("mh", "teh", "mhteh@gmail.com", "mhteh");
        User user2 = new User("alvin", "tang", "alvintang@gmail.com", "alvintang");
        User user3 = new User("pj", "lee", "pjlee@gmail.com", "pjlee");
        User user4 = new User("ys", "lau", "yslau@gmail.com", "yslau");
        User user5 = new User("yh", "lim", "yhlim@gmail.com", "yhlim");
        User user6 = new User("alvin1", "lim", "yhdwalim@gmail.com", "yhlim");
        User user7 = new User("alvin2", "lim", "yhldwadim@gmail.com", "yhlim");
        User user8 = new User("yhdwa", "lim", "yhli324m@gmail.com", "yhlim");
        User user9 = new User("ydwah", "lim", "yhlidawcsm@gmail.com", "yhlim");
        User user10 = new User("y432h", "lim", "yhlegsefim@gmail.com", "yhlim");

        initialUserList.add(user1);
        initialUserList.add(user2);
        initialUserList.add(user3);
        initialUserList.add(user4);
        initialUserList.add(user5);
        initialUserList.add(user6);
        initialUserList.add(user7);
        initialUserList.add(user8);
        initialUserList.add(user9);
        initialUserList.add(user10);


        return initialUserList;
    }
}
