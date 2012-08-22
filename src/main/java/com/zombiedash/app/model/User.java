package com.zombiedash.app.model;

public class User implements java.io.Serializable {
    String userName;
    char[] password;
    Boolean loggedStatus = false;
    private Role userRole;


    public User(String userName, char[] password) {
        this.userName = userName;
        this.password = password;
        this.userRole = Role.GAME_DESIGNER;
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

    public Role getUserRole() {
        return userRole;
    }

    public void setRole(Role role) {
        this.userRole = role;
    }

}