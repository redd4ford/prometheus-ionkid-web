package com.prometheus.ionkid.rest.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "\"user\"")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  protected Integer id;
  @Column
  protected String googleId = null;
  @Column
  protected String email;
  @Column
  protected String phoneNumber;
  @Column
  protected String password;
  @Column
  protected String firstName;
  @Column
  protected String lastName;
  @Column
  protected String username;
  @Column
  protected String avatarUrl;
  @Column
  protected String gender;
  @Column
  protected String country;
  @Column
  protected String city;
  @Column
  protected Date dateOfBirth;
  @Column
  protected LocalDateTime lastVisit;
  @Column
  protected Boolean active;
  @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
  @Enumerated(EnumType.STRING)
  private Set<Role> roles;
  @OneToMany(mappedBy = "user")
  private List<Comment> comments = new ArrayList<Comment>();

  public User() {
  }

  public User(String email, String password, Boolean active) {
    this.email = email;
    this.password = password;
    this.active = active;
  }

  public User(String googleId, String email, String phoneNumber, String password, String firstName,
              String lastName, String avatarUrl, String gender, String country, String city,
              Date dateOfBirth, LocalDateTime lastVisit, Boolean active) {
    this.googleId = googleId;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    setUsername(firstName, lastName);
    this.avatarUrl = avatarUrl;
    this.gender = gender;
    this.country = country;
    this.city = city;
    this.dateOfBirth = dateOfBirth;
    this.lastVisit = lastVisit;
    this.active = active;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getGoogleId() {
    return googleId;
  }

  public void setGoogleId(String googleId) {
    this.googleId = googleId;
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

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return getRoles();
  }

  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setUsername(String firstName, String lastName) {
    this.username = (firstName + lastName).toLowerCase();
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
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

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public LocalDateTime getLastVisit() {
    return lastVisit;
  }

  public void setLastVisit(LocalDateTime lastVisit) {
    this.lastVisit = lastVisit;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return getActive();
  }

}