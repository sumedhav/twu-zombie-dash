package com.zombiedash.app.model;

public class User {
    public String getUserName() {
        return userName;
    }

    public char[] getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;

    }

    public void setPassword(char[] password) {
        this.password = password;
    }

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