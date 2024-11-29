package com.example.demo.Database;

import com.example.demo.entity.User;

import java.util.ArrayList;
import java.util.List;

public interface DB {
    List<User> USERS = new ArrayList<User>(List.of(
            new User("Ali", "admin", "root123", true),
            new User("Vali", "user", "123", false)
    ));


}
