package com.prometheus.ionkid.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role implements GrantedAuthority {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column
  private String roleName;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users_roles",
      joinColumns = {@JoinColumn(name = "role_id", nullable = false)},
      inverseJoinColumns = {@JoinColumn(name = "user_id", nullable = true)})
  @JsonIgnoreProperties("roles")
  private Set<User> users;

  public Role() {
  }

  public Role(String roleName) {
    this.roleName = roleName;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }

  @Override
  public String getAuthority() {
    return getRoleName();
  }
}
