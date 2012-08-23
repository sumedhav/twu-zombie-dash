package com.zombiedash.app.model;

public class User implements java.io.Serializable {
    private String userName;
    private String password;
    private Role userRole;
    private String name;
    private String email;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.userRole = Role.GAME_DESIGNER;
        this.name = "Mr. Mysterious";
    }

    public User(String userName, String password, Role userRole, String name, String email) {
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
        this.name = name;
        this.email = email;
    }

    public boolean authenticate(String userName, String password) {
        boolean properUserName = userName.equals(this.userName);
        boolean properPassword = password.equals(this.password);
        return properUserName && properPassword;
    }

    public Role getUserRole() {
        return userRole;
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
        return userRole == user.userRole;

    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        return result;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Integer getRole() {
        return userRole.getVal();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}