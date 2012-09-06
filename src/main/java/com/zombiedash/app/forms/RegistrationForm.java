package com.zombiedash.app.forms;

import com.zombiedash.app.model.Role;

import java.util.List;

public class RegistrationForm {
    private UserForm userForm;
    private String userName = "";
    private String fullName = "";
    private String email = "";
    private String password = "";

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> validate() {
        userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setFullName(fullName);
        userForm.setEmail(email);
        userForm.setPassword(password);
        return userForm.validate();
    }
}
