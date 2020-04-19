package com.prometheus.ionkid.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Parent {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer parentId;

  @Column
  private String password;
  @Column
  private String username;
  @Column
  private String country;
  @Column
  private String city;
  @Column
  private String email;
  @Column
  private String phoneNumber;
  @Column
  private int userAccess;
  @ManyToMany(mappedBy = "parents")
  private Set<Kid> kids = new HashSet<Kid>();

  public Parent() {
  }

  public Parent(String username, String password, String country, String city, String email,
                String phoneNumber) {
    this.username = username;
    this.password = password;
    this.country = country;
    this.city = city;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }

  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public int getUserAccess() {
    return userAccess;
  }

  public void setUserAccess(int userAccess) {
    this.userAccess = userAccess;
  }

}
