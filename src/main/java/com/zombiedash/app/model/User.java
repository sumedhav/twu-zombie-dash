//Responsibility: Hold the contents of a user domain object
package com.zombiedash.app.model;

import java.util.UUID;

public class User implements java.io.Serializable {
    private String userName;
    private Role userRole;
    private String name;
    private String email;
    private UUID userId;

    public User(String userName) {
        this(userName, Role.GAME_DESIGNER, "Default Name", "default@mail.com");
    }

    public User(String userName, Role userRole, String name, String email) {
        this.userName = userName;
        this.userRole = userRole;
        this.name = name;
        this.email = email;
    }

    public User(UUID userId, String userName, Role userRole, String name, String email) {
        this(userName, userRole, name, email);
        this.userId = userId;
    }

    public Role getUserRole() {
        return userRole;
    }

    @Override
    public String toString() {
        return "Username: " + userName + ", Role: " + userRole + ", Name: " + name + ", Email: " + email;
    }

    public String getUserName() {
        return userName;
    }


    public Role getRole() {
        return userRole;
    }

    public Integer getRoleVal() {
        return userRole.getVal();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UUID getUserId() {
        return userId;
    }
}