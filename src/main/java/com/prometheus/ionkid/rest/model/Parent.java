package com.prometheus.ionkid.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
public class Parent extends User {

  @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonIgnoreProperties("parent")
  public Set<Kid> kids;

  public Parent() {
  }

  public Parent(String googleId, String email, String phoneNumber, String password, String firstName,
                String lastName, String avatarUrl, String gender, String country, String city,
                Date dateOfBirth, LocalDateTime lastVisit, Boolean active) {
    super(googleId, email, phoneNumber, password, firstName, lastName, avatarUrl, gender, country,
        city, dateOfBirth, lastVisit, active);
  }

  public Set<Kid> getKids() {
    return kids;
  }

  public void setKids(Set<Kid> kids) {
    this.kids = kids;
  }
}
