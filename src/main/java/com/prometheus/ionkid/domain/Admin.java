package com.prometheus.ionkid.domain;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)

  private int adminId;
  private String username;
  private String password;
  private boolean active;

  public Admin() {
  }

  public Admin(String username, String password, boolean active) {
    this.username = username;
    this.password = password;
    this.active = active;
  }

  public Integer getAdminId() {
    return adminId;
  }

  public void setAdminId(Integer id) {
    this.adminId = id;
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

  public boolean getActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}