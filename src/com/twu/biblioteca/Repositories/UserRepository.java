package com.twu.biblioteca.Repositories;

import com.twu.biblioteca.Models.User;

import java.util.ArrayList;

public class UserRepository {

    ArrayList<User> users;

    public UserRepository(){
        getUsers();
    }

    public ArrayList<User> getUsers(){

        users = new ArrayList<User>();

        User andressa = new User(0, "000-0001", "Andressa", "123#", "neumm.andressa@gmail.com", 981007669);
        User rafael = new User(1, "000-0002", "Rafael", "1234#", "raf@gmail.com", 123456789);

        users.add(andressa);
        users.add(rafael);

        return users;
    }
}
