package com.prometheus.ionkid.rest.model;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
public class Admin extends User {

  public Admin() {
  }

  public Admin(String googleId, String email, String phoneNumber, String password, String firstName,
               String lastName, String avatarUrl, String gender, String country, String city,
               Date dateOfBirth, LocalDateTime lastVisit, Boolean active) {
    super(googleId, email, phoneNumber, password, firstName, lastName, avatarUrl, gender, country,
        city, dateOfBirth, lastVisit, active);
  }

}