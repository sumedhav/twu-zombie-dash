package com.zombiedash.app.model;

public class User implements java.io.Serializable {
    String userName;
    String password;


    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public boolean authenticate(String userName, String password) {
        boolean properUserName = userName.equals(this.userName);
        boolean properPassword = password.equals(this.password);
        return properUserName && properPassword;
    }

}