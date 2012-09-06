//Responsibility: Validate a User and pass around as a bean
package com.zombiedash.app.forms;

import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

public class UserForm {
    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private String userName;
    private String password;
    private String role;
    private String fullName;
    private String email;

    private List<String> errorCodes = new ArrayList<String>();

    public UserForm() {}


    public ModelMap populateFormValuesIntoMap(){
        ModelMap modelMap  = new ModelMap();
        modelMap.put("username", userName);
        modelMap.put("password", password);
        modelMap.put("role", role);
        modelMap.put("fullName", fullName);
        modelMap.put("email", email);
        return  modelMap;
    }

    public User createUser() {
       return new User(userName, Role.generateRole(role), fullName, email);
    }

    public List<String> validate() {
        fullName = fullName.trim();
        validateField(userName, "usernameFieldEmpty","invalidUserName","([a-zA-Z0-9]){5,40}");
        validateField(password, "passwordFieldEmpty", "invalidPassword", "(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,40})$");
        validateField(email, "emailFieldEmpty", "invalidEmail", "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        validateField(fullName, "nameFieldEmpty", "invalidName", "([a-zA-Z ]){1,40}");
        return errorCodes;
    }

    private void validateField(String field, String emptyFieldMessage, String invalidFieldMessage, String matchCriteria) {
        if(isEmpty(field))
            errorCodes.add(emptyFieldMessage);
        else if(!field.matches(matchCriteria))
            errorCodes.add(invalidFieldMessage);
    }

    private boolean isEmpty(String field) {
        return field==null||field.isEmpty();
    }

    public String getPassword() {
        return password;
    }

    public boolean hasErrors() {
        return errorCodes.size()!=0;
    }

    public List getErrorCodes() {
        return errorCodes;
    }
}