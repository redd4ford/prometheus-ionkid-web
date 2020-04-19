package com.prometheus.ionkid.domain;

import javax.persistence.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Doctor {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)

  private Integer doctorId;

  @Column
  private String name;
  @Column
  private String gender;
  @Column
  private String organization;
  @Column
  private String specialty;
  @Column
  private String country;
  @Column
  private String city;
  @Column
  private String dateOfBirth;
  @Column
  private String email;
  @Column
  private String phoneNumber;
  @Column
  private String password;
  @Column
  private int userAccess;
  @OneToMany(mappedBy = "doctor")
  private List<Program> programs = new ArrayList<Program>();

  public Doctor() {
  }

  public Doctor(String name, String password, String gender, String dateOfBirth,
                String organization, String specialty, String country, String city, String email,
                String phoneNumber) throws ParseException {
    this.name = name;
    this.gender = gender;
    this.organization = organization;
    this.specialty = specialty;
    this.country = country;
    this.city = city;
    this.dateOfBirth = dateOfBirth;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getOrganization() {
    return organization;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
  }

  public String getSpecialty() {
    return specialty;
  }

  public void setSpecialty(String specialty) {
    this.specialty = specialty;
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

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Integer getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(Integer doctorId) {
    this.doctorId = doctorId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getUserAccess() {
    return userAccess;
  }

  public void setUserAccess(int userAccess) {
    this.userAccess = userAccess;
  }

}