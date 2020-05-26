package com.prometheus.ionkid.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Doctor extends User {

  @Column
  private String certificationId;

  @Column
  private String organization;

  @Column
  private String specialty;

  @OneToMany(mappedBy = "doctor")
  private List<Program> programs = new ArrayList<Program>();

  public Doctor() {
  }

  public Doctor(String googleId, String email, String phoneNumber, String password, String firstName,
                String lastName, String avatarUrl, String gender, String country, String city,
                String dateOfBirth, LocalDateTime lastVisit, Boolean active, String certificationId,
                String organization, String specialty) {
    super(googleId, email, phoneNumber, password, firstName, lastName, avatarUrl, gender, country,
        city, dateOfBirth, lastVisit, active);
    this.certificationId = certificationId;
    this.organization = organization;
    this.specialty = specialty;
  }

  public Doctor(String email, String password, boolean active) {
    this.email = email;
    this.password = password;
    this.active = active;
  }

  public String getCertificationId() {
    return certificationId;
  }

  public void setCertificationId(String certificationId) {
    this.certificationId = certificationId;
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

  public List<Program> getPrograms() {
    return programs;
  }

  public void setPrograms(List<Program> programs) {
    this.programs = programs;
  }

}