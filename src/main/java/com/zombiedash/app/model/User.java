package com.zombiedash.app.model;

public class User implements java.io.Serializable {
    private String userName;
    private String password;
    private Role userRole;
    private String name;
    private String email;

    public User(String userName, String password) {
        this(userName,  password, Role.GAME_DESIGNER, "Mr. Mysterious", "mysterious@zombie.com");
    }

    public User(String userName, String password, Role userRole, String name, String email) {
        if(userName.matches("([a-zA-Z0-9]*\\s[a-zA-Z0-9]*)+")) throw new IllegalArgumentException("invalidUserName");
        if(!password.matches("(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,40})$")) throw new IllegalArgumentException("invalidPassword");
        if(!email.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) throw  new IllegalArgumentException("invalidEmail");
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
        this.name = name;
        this.email = email;
    }

    public Role getUserRole() {
        return userRole;
    }

    @Override
    public String toString() {
        return "Username: " + userName + ", Password: " + password + ", Role: " + userRole + ", Name: " + name + ", Email: " + email;
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