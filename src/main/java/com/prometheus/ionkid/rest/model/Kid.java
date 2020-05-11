package com.prometheus.ionkid.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Kid {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column
  private String firstName;
  @Column
  private String lastName;
  @Column
  private String avatarUrl;
  @Column
  private Date dateOfBirth;
  @Column
  private String country;
  @Column
  private String city;
  @Column
  private String gender;
  @Column
  private String diagnose;
  @Column
  private Integer weight;
  @Column
  private Integer height;
  @Column
  private String bloodType;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "parent_id")
  @JsonIgnoreProperties("kids")
  private Parent parent;

  @OneToOne
  @JoinColumn(name = "program_id")
  private Program program;

  public Kid() {
  }

  public Kid(String firstName, String lastName, String avatarUrl, Date dateOfBirth, String country,
             String city, String gender, String diagnose, Integer weight, Integer height,
             String bloodType) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.avatarUrl = avatarUrl;
    this.dateOfBirth = dateOfBirth;
    this.country = country;
    this.city = city;
    this.gender = gender;
    this.diagnose = diagnose;
    this.weight = weight;
    this.height = height;
    this.bloodType = bloodType;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
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

  public Parent getParent() {
    return parent;
  }

  public void setParent(Parent parent) {
    this.parent = parent;
  }

  public Program getProgram() {
    return program;
  }

  public void setProgram(Program program) {
    this.program = program;
  }

}
