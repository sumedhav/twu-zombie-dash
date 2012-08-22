package com.zombiedash.app.model;

public class User implements java.io.Serializable {
    String userName;
    String password;
    private Role userRole;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.userRole = Role.GAME_DESIGNER;
    }

    public boolean authenticate(String userName, String password) {
        boolean properUserName = userName.equals(this.userName);
        boolean properPassword = password.equals(this.password);
        return properUserName && properPassword;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role role) {
        this.userRole = role;
    }

}