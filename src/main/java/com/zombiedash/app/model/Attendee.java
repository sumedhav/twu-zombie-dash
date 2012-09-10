package com.zombiedash.app.model;

public class Attendee {
    private User user;
    private String dob;
    private String country;
    private String zipcode;
    private String address;
    private String phoneNo;

    public Attendee(User user,
                    String dob,
                    String country,
                    String phoneNo,
                    String address,
                    String zipcode) {
        this.user = user;
        this.dob = dob;
        this.country = country;
        this.phoneNo=phoneNo;
        this.address=address;
        this.zipcode=zipcode;
    }

    public String getUsername() {
        return user.getUserName();
    }


    public String getName() {
        return user.getName();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getDob() {
        return dob;
    }

    public String getCountry() {
        return country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
}
