//Responsibility: Validate a User and pass around as a bean
package com.zombiedash.app.forms;

import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class UserForm {
    @Setter
    private String userName;
    @Setter
    private String password;
    @Setter
    private String role;
    @Setter
    private String name;
    @Setter
    private String email;

    private List<String> errorCodes = new ArrayList<String>();

    public UserForm(String userName, String role, String name, String email, String password){
        this.userName = userName;
        this.role = role;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public ModelMap populateFormValuesIntoMap(){
        ModelMap modelMap  = new ModelMap();
        modelMap.put("username", userName);
        modelMap.put("password", password);
        modelMap.put("role", role);
        modelMap.put("name", name);
        modelMap.put("email", email);
        return  modelMap;
    }

    public User createUser() {
       return new User(userName, Role.generateRole(role), name, email);
    }

    public List<String> validate() {
        name= name.trim();
        validateField(userName, "usernameFieldEmpty","invalidUserName","([a-zA-Z0-9]){5,40}");
        validateField(password, "passwordFieldEmpty", "invalidPassword", "(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,40})$");
        validateField(email, "emailFieldEmpty", "invalidEmail", "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        validateField(name, "nameFieldEmpty", "invalidName", "([a-zA-Z ]){1,40}");
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