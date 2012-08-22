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

    @Override
    public String toString() {
        return "Username: " + userName + ", Password: " + password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (userRole != user.userRole) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        return result;
    }
}