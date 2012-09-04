package com.zombiedash.app.model;

public class Attendee {
  private String username;
  private String password;
  private String name;
  private String email;
  private String dob;
  private String country;
  private String conference;

  public Attendee(String username, String password, String name, String email, String dob, String conference, String country) {
    this.username = username;
    this.password = password;
    this.name = name;
    this.email = email;
    this.dob = dob;
    this.conference = conference;
    this.country = country;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getDob() {
    return dob;
  }

  public String getCountry() {
    return country;
  }

  public String getConference() {
    return conference;
  }
}
