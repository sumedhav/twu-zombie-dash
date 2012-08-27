package com.zombiedash.app.model;

public class User implements java.io.Serializable {
    private String userName;
    private String password;
    private Role userRole;
    private String name;
    private String email;

    public User(String userName, String password) {
        if(userName.matches("([a-zA-Z0-9]*\\s[a-zA-Z0-9]*)+")) throw new IllegalArgumentException("The Username cannot have a whitespace character.");
        if(!password.matches("(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,40})$")) throw new IllegalArgumentException("The password must have at least 6 alphanumeric characters.");
        this.userName = userName;
        this.password = password;
        this.userRole = Role.GAME_DESIGNER;
        this.name = "Mr. Mysterious";
    }

    public User(String userName, String password, Role userRole, String name, String email) {
        if(userName.matches("([a-zA-Z0-9]*\\s[a-zA-Z0-9]*)+")) throw new IllegalArgumentException("The Username cannot have a whitespace character.");
        if(!password.matches("(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,40})$")) throw new IllegalArgumentException("The password must have at least 6 alphanumeric characters.");
        if(!email.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) throw  new IllegalArgumentException("The email address is not valid.");
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
        return "Username: " + userName + ", Password: " + password + ", Role: " + userRole + ", Name: " + name + ", Email: " + email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
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
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole(){
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