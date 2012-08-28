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

    List errorCodes = new ArrayList();

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

    public List validate() {
        if(isEmpty(userName)||isEmpty(password)||isEmpty(role)||isEmpty(name)||isEmpty(email))
            errorCodes.add("allFieldsAreMandatory") ;
        if(!password.matches("(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,40})$"))
            errorCodes.add("invalidPassword");
        if(!userName.matches("([a-zA-Z0-9]){5,40}"))
            errorCodes.add("invalidUserName");
        if(!email.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
            errorCodes.add("invalidEmail");

        return errorCodes;
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