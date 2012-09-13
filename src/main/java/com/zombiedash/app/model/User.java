//Responsibility: Hold the contents of a user domain object
package com.zombiedash.app.model;

public class User implements java.io.Serializable {
    private String userName;
    private Role userRole;
    private String name;
    private String email;

    public User(String userName, Role userRole, String name, String email) {
        this.userName=userName;
        this.name=name;
        this.userRole=userRole;
        this.email=email;
    }

    public Role getUserRole() {
        return userRole;
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

}