package com.prometheus.ionkid.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
public class Parent extends User {

  @JsonIgnoreProperties("parentEmail")
  public List<Kid> kids;

  public Parent(String email, String phoneNumber, String firstName, String lastName,
                String avatarUrl, String country, String city, Date dateOfBirth,
                LocalDateTime lastVisit, List<Kid> kids) {
    super(email, false, true, phoneNumber, firstName, lastName, avatarUrl, country, city,
        dateOfBirth, lastVisit);
    this.kids = kids;
  }

  public Parent(List<Kid> kids) {
    this.kids = kids;
  }

  public Parent() {
  }

  public List<Kid> getKids() {
    return kids;
  }

  public void setKids(List<Kid> kids) {
    this.kids = kids;
  }

}
