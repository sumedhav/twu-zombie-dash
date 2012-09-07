package com.zombiedash.app.forms;

import java.util.List;

public class RegistrationForm {
    private UserForm userForm;
    private String userName = "";
    private String fullName = "";
    private String email = "";
    private String password = "";
    private String address = "";
    private String password2= "";
    private final static int ADDRESS_MAX_LENGTH = 100;
    private final static int PHONENO_MAX_LENGTH = 20;
    private final static int ZIPCODE_MAX_LENGTH = 20;
    private String phoneNo = "";
    private String zipcode = "";

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

    public List<String> validate() {
        userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setFullName(fullName);
        userForm.setEmail(email);
        userForm.setPassword(password);
        List<String> errorCodes = userForm.validate();
        if (address.isEmpty()) {errorCodes.add("addressFieldEmpty");}
        if (phoneNo.isEmpty()) {errorCodes.add("phoneNoFieldEmpty");}
        if (zipcode.isEmpty()) {errorCodes.add("zipcodeFieldEmpty");}
        if (address.length() > ADDRESS_MAX_LENGTH) {errorCodes.add("invalidAddress");}
        if (phoneNo.length() > PHONENO_MAX_LENGTH) {errorCodes.add("invalidPhoneNo");}
        if (zipcode.length() > ZIPCODE_MAX_LENGTH) {errorCodes.add("invalidZipcode");}
        if (!password.equals(password2)) {errorCodes.add("passwordMismatch");}
        return errorCodes;
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
}
