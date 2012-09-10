package com.zombiedash.app.forms;

import com.zombiedash.app.model.Attendee;
import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationForm {
    public static final String ATTENDEE = "attendee";
    private UserForm userForm;
    private String userName = "";
    private String fullName = "";
    private String email = "";
    private String password = "";
    private String password2= "";
    private String dob = "";
    private String address = "";
    private String phoneNo = "";
    private String zipcode = "";
    private String country = "";
    private final static int ADDRESS_MAX_LENGTH = 100;
    private final static int PHONENO_MAX_LENGTH = 20;
    private final static int ZIPCODE_MAX_LENGTH = 20;
    private List<String> errorCodes;
    public RegistrationForm(){
        userForm = new UserForm();
    }

    public void validate() {
        userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setFullName(fullName);
        userForm.setEmail(email);
        userForm.setPassword(password);
        errorCodes = userForm.validate();
        if (address.length() > ADDRESS_MAX_LENGTH) {errorCodes.add("invalidAddress");}
        if (phoneNo.length() > PHONENO_MAX_LENGTH) {errorCodes.add("invalidPhoneNo");}
        if (zipcode.length() > ZIPCODE_MAX_LENGTH) {errorCodes.add("invalidZipcode");}
        if (!password.equals(password2)) {errorCodes.add("passwordMismatch");}
    }

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

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public boolean hasErrors() {
        return  errorCodes.size() != 0;
    }

    public List<String> getErrors() {
        return errorCodes;
    }

    public Attendee createAttendee() {
        return new Attendee(new User(userName, Role.generateRole(ATTENDEE), fullName, email), dob, country, phoneNo, address, zipcode);
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Map populateFormValuesToModelMap() {
        HashMap<String, String> model = new HashMap<String, String>();
        model.put("username", userName);
        model.put("name", fullName);
        model.put("email", email);
        model.put("dob", dob);
        model.put("address", address);
        model.put("country", country);
        model.put("zipCode", zipcode);
        model.put("phoneNo", phoneNo);
        return model;
    }
}
