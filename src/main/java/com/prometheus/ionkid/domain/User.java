package com.prometheus.ionkid.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "\"user\"")
public class User implements OAuth2User {
  @Id
  private String id;
  @Column
  private String firstName;
  @Column
  private String lastName;
  @Column
  private String username;
  @Column
  private String userpic;
  @Column
  private String email;
  @Column
  private String gender;
  @Column
  private String locale;
  @Column
  private LocalDateTime lastVisit;

  @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
  @Enumerated(EnumType.STRING)
  private Set<Role> roles;

  public User() {
  }

  public User(String firstName, String lastName, String userpic, String email, String gender,
              String locale, LocalDateTime lastVisit) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = (firstName + lastName).toLowerCase();
    this.userpic = userpic;
    this.email = email;
    this.gender = gender;
    this.locale = locale;
    this.lastVisit = lastVisit;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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
  public String getName() {
    return this.firstName + " " + this.lastName;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUserpic() {
    return userpic;
  }

  public void setUserpic(String userpic) {
    this.userpic = userpic;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

  public LocalDateTime getLastVisit() {
    return lastVisit;
  }

  public void setLastVisit(LocalDateTime lastVisit) {
    this.lastVisit = lastVisit;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return getRoles();
  }

  @Override
  public <A> A getAttribute(String name) {
    return null;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return null;
  }

}