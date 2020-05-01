package com.prometheus.ionkid.rest.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.LocalDateTime;
import java.util.Date;


@Entity

public class Admin extends User {

  public Admin() {
    //add an "ADMIN" role to any user in this class
  }

  public Admin(String googleId, String email, String phoneNumber, String password, String firstName,
               String lastName, String avatarUrl, String gender, String country, String city,
               Date dateOfBirth, LocalDateTime lastVisit, Boolean active) {
    super(googleId, email, phoneNumber, password, firstName, lastName, avatarUrl, gender, country,
        city, dateOfBirth, lastVisit, active);
  }

}