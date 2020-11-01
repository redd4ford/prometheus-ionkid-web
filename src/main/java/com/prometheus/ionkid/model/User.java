package com.prometheus.ionkid.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
public  class User {

  // DOC ID
  protected String email;

  private boolean isDoctor;

  private boolean isParent;

  protected String phoneNumber;

  protected String firstName;

  protected String lastName;

  protected String avatarUrl;

  protected String country;

  protected String city;

  protected Date dateOfBirth;

  protected LocalDateTime lastVisit;

  public User(String email, boolean isDoctor, boolean isParent, String phoneNumber,
              String firstName, String lastName, String avatarUrl, String country, String city,
              Date dateOfBirth, LocalDateTime lastVisit) {
    this.email = email;
    this.isDoctor = isDoctor;
    this.isParent = isParent;
    this.phoneNumber = phoneNumber;
    this.firstName = firstName;
    this.lastName = lastName;
    this.avatarUrl = avatarUrl;
    this.country = country;
    this.city = city;
    this.dateOfBirth = dateOfBirth;
    this.lastVisit = lastVisit;
  }

  public User() {
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isDoctor() {
    return isDoctor;
  }

  public void setDoctor(boolean doctor) {
    isDoctor = doctor;
  }

  public boolean isParent() {
    return isParent;
  }

  public void setParent(boolean parent) {
    isParent = parent;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public LocalDateTime getLastVisit() {
    return lastVisit;
  }

  public void setLastVisit(LocalDateTime lastVisit) {
    this.lastVisit = lastVisit;
  }

}
