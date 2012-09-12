package com.zombiedash.app.forms;

import com.zombiedash.app.model.Attendee;
import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationForm {
    public static final String ATTENDEE = "attendee";
    private UserForm userForm;
    private String userName = "";

    private String name = "";

    private String email = "";
    private String password = "";
    private String password2= "";
    private String dob = "";
    private String address = "";
    private String phoneNo = "";
    private String zipcode = "";
    private String countrylist = "";
    private final static int ADDRESS_MAX_LENGTH = 100;
    private final static int PHONENO_MAX_LENGTH = 20;
    private final static int ZIPCODE_MAX_LENGTH = 20;
    private List<String> errorCodes;
    private String productsMailingConfirmation;
    private String adsConfirmation;

    public RegistrationForm(){
        userForm = new UserForm();
    }
    public void validate() {
        userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setFullName(name);
        userForm.setEmail(email);
        userForm.setPassword(password);
        errorCodes = userForm.validate();
        validateDOB(dob);
        if(countrylist.equals("")) {errorCodes.add("countryNotSelected");}
        if (address.length() > ADDRESS_MAX_LENGTH) {errorCodes.add("invalidAddress");}
        if (phoneNo.length() > PHONENO_MAX_LENGTH) {errorCodes.add("invalidPhoneNo");}
        if (zipcode.length() > ZIPCODE_MAX_LENGTH) {errorCodes.add("invalidZipcode");}
        if (!password.equals(password2)) {errorCodes.add("passwordMismatch");}
    }

    private void validateDOB(String date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try{
            Date parsedDate = dateFormat.parse(date);
            Date nineteenHundred = dateFormat.parse("1900-01-01");
            Date currentDate = new Date();
            if(parsedDate.after(currentDate) || parsedDate.before(nineteenHundred)) {errorCodes.add("invalidDOB");}
        }catch (ParseException e) {
            if(date.equals("")) {errorCodes.add("dobFieldEmpty");}
            else {errorCodes.add("invalidDateFormat");}
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setName(String name) {
        this.name = name;
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
        return new Attendee(new User(userName, Role.generateRole(ATTENDEE), name, email), dob, countrylist, phoneNo, address, zipcode, productsMailingConfirmation.equals("on"), adsConfirmation.equals("on"));
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCountrylist() {
        return countrylist;
    }

    public void setCountrylist(String countrylist) {
        this.countrylist = countrylist;
    }

    public String getName() {
        return name;
    }

    public Map populateFormValuesToModelMap() {
        HashMap<String, String> model = new HashMap<String, String>();
        model.put("username", userName);
        model.put("name", name);
        model.put("email", email);
        model.put("dob", dob);
        model.put("address", address);
        model.put("countrylist", countrylist);
        model.put("zipCode", zipcode);
        model.put("phoneNo", phoneNo);
        model.put("productsMailingConfirmation", productsMailingConfirmation);
        model.put("adsConfirmation", adsConfirmation);
        return model;
    }

    public void setProductsMailingConfirmation(String productsMailingConfirmation) {
        this.productsMailingConfirmation = productsMailingConfirmation;
    }

    public void setAdsConfirmation(String adsConfirmation) {
        this.adsConfirmation = adsConfirmation;
    }
}
