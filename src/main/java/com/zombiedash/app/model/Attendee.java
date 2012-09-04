package com.zombiedash.app.model;

public class Attendee {
  private String username;
  private String password;
  private String name;
  private String email;
  private String dob;
  private String country;

  public Attendee(String username, String password, String name, String email, String dob, String country) {
    this.username = username;
    this.password = password;
    this.name = name;
    this.email = email;
    this.dob = dob;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Attendee attendee = (Attendee) o;

    if (country != null ? !country.equals(attendee.country) : attendee.country != null) return false;
    if (dob != null ? !dob.equals(attendee.dob) : attendee.dob != null) return false;
    if (email != null ? !email.equals(attendee.email) : attendee.email != null) return false;
    if (name != null ? !name.equals(attendee.name) : attendee.name != null) return false;
    if (password != null ? !password.equals(attendee.password) : attendee.password != null) return false;
    if (username != null ? !username.equals(attendee.username) : attendee.username != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = username != null ? username.hashCode() : 0;
    result = 31 * result + (password != null ? password.hashCode() : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (dob != null ? dob.hashCode() : 0);
    result = 31 * result + (country != null ? country.hashCode() : 0);
    return result;
  }
}
