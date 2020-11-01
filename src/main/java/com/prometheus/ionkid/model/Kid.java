package com.prometheus.ionkid.model;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Kid {

  // DOC ID
  private Integer docID;

  private String parentEmail;

  private Program program;

  private SKIP skip;

  private String firstName;

  private String lastName;

  private String avatarUrl;

  private String country;

  private String city;

  private Date dateOfBirth;

  // TODO gender enum
  private String gender;

  private String diagnose;

  private Integer weight;

  private Integer height;

  // TODO bloodtype enum
  private String bloodType;

  public Kid(Integer docID, String parentEmail, Program program, SKIP skip, String firstName,
             String lastName, String avatarUrl, String country, String city, Date dateOfBirth,
             String gender, String diagnose, Integer weight, Integer height, String bloodType) {
    this.docID = docID;
    this.parentEmail = parentEmail;
    this.program = program;
    this.skip = skip;
    this.firstName = firstName;
    this.lastName = lastName;
    this.avatarUrl = avatarUrl;
    this.country = country;
    this.city = city;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
    this.diagnose = diagnose;
    this.weight = weight;
    this.height = height;
    this.bloodType = bloodType;
  }

  public Kid() {
  }

  public Integer getDocID() {
    return docID;
  }

  public void setDocID(Integer docID) {
    this.docID = docID;
  }

  public String getParentEmail() {
    return parentEmail;
  }

  public void setParentEmail(String parentEmail) {
    this.parentEmail = parentEmail;
  }

  public Program getProgram() {
    return program;
  }

  public void setProgram(Program program) {
    this.program = program;
  }

  public SKIP getSkip() {
    return skip;
  }

  public void setSkip(SKIP skip) {
    this.skip = skip;
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

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getDiagnose() {
    return diagnose;
  }

  public void setDiagnose(String diagnose) {
    this.diagnose = diagnose;
  }

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public String getBloodType() {
    return bloodType;
  }

  public void setBloodType(String bloodType) {
    this.bloodType = bloodType;
  }
}
