package com.prometheus.ionkid.rest.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

  DOCTOR, ADMIN, PARENT;

  @Override
  public String getAuthority() {
    return name();
  }

}