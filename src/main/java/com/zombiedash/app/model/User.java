package com.zombiedash.app.model;

public class User implements java.io.Serializable {
    String userName;
    char[] password;
    Boolean loggedStatus = false;


    public User(String userName, char[] password) {
        this.userName = userName;
        this.password = password;
    }

    public void authenticate(String userName, char[] password) {
        boolean properUserName = userName.equals(this.userName);
        boolean properPassword = new String(password).equals(new String(this.password));
        if(!(properUserName &&  properPassword)) throw new RuntimeException("Authentication Failure");
        loggedStatus = true;
    }

    public Boolean isLoggedIn() {
        return loggedStatus;
    }
}