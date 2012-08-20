package com.zombiedash.app.model;

public class User {
    String userName;
    char[] password;

    public User(String userName, char[] password) {
        this.userName = userName;
        this.password = password;
    }

    public void authenticate(String userName, char[] password) {
        boolean properUserName = userName.equals(this.userName);
        boolean properPassword = new String(password).equals(new String(this.password));
        if(!(properUserName &&  properPassword)) throw new RuntimeException("Authentication Failure");
    }
}