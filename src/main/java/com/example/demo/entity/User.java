package com.example.demo.entity;

public class User {

    private int id = idGen++;
    private String name;
    private String login;
    private String password;
    private Boolean status;
    private static Integer idGen = 1;

    public User(String name, String login, String password, Boolean status) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.status = status;
    }

}
