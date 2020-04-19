package com.prometheus.ionkid.domain;

import javax.persistence.*;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Kid {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer kidId;

  @Column
  private String name;
  @Column
  private String gender;
  @Column
  private String diagnose;
  @Column
  private String country;
  @Column
  private String city;
  @Column
  private String dateOfBirth;
  @Column
  private Integer weight;
  @Column
  private Integer height;
  @Column
  private String bloodType;
  @ManyToOne
  @JoinColumn(name = "programId")
  private Program program;
  @ManyToMany
  @JoinTable(
      name = "kidParent",
      joinColumns = {@JoinColumn(name = "kidId")},
      inverseJoinColumns = {@JoinColumn(name = "parentId")}
  )
  private Set<Parent> parents = new HashSet<Parent>();

  public Kid() {
  }

  public Kid(String name, String gender, String diagnose, String country, String city,
             String dateOfBirth, Integer weight, Integer height, String bloodType)
      throws ParseException {
    this.name = name;
    this.gender = gender;
    this.diagnose = diagnose;
    this.country = country;
    this.city = city;
    this.dateOfBirth = dateOfBirth;
    this.weight = weight;
    this.height = height;
    this.bloodType = bloodType;
  }

  public Integer getKidId() {
    return kidId;
  }

  public void setKidId(Integer id) {
    this.kidId = id;
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

  public String getDiagnose() {
    return diagnose;
  }

  public void setDiagnose(String diagnose) {
    this.diagnose = diagnose;
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
