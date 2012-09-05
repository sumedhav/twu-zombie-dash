package com.zombiedash.app.model;

public class Attendee {
  private User user;
  private String dob;
  private String country;

  public Attendee(User user,String dob, String country) {
    this.user = user;
    this.dob = dob;
    this.country = country;
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

}
