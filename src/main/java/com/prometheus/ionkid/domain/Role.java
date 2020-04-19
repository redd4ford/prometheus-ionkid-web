package com.prometheus.ionkid.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
  ADMIN, DOCTOR;

  @Override
  public String getAuthority() {
    return name();
  }
}
