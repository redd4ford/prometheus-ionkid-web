package com.prometheus.ionkid.model;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class Doctor extends User {

  private String certificationID;

  private String organization;

  private String specialty;

  public Doctor(String email, String phoneNumber, String firstName, String lastName,
                String avatarUrl, String country, String city, Date dateOfBirth,
                LocalDateTime lastVisit, String certificationID, String organization,
                String specialty) {
    super(email, true, false, phoneNumber, firstName, lastName, avatarUrl, country, city,
        dateOfBirth, lastVisit);
    this.certificationID = certificationID;
    this.organization = organization;
    this.specialty = specialty;
  }

  public Doctor(String certificationID, String organization, String specialty) {
    this.certificationID = certificationID;
    this.organization = organization;
    this.specialty = specialty;
  }

  public Doctor() {
  }

  public String getCertificationID() {
    return certificationID;
  }

  public void setCertificationID(String certificationID) {
    this.certificationID = certificationID;
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

}
