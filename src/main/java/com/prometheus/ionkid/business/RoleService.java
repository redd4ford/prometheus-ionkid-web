package com.prometheus.ionkid.business;

import com.prometheus.ionkid.dataaccess.RoleRepository;
import com.prometheus.ionkid.rest.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends AbstractService<Role> {
  @Autowired
  private RoleRepository roleRepository;

  @Override
  protected JpaRepository<Role, Integer> getRepository() {
    return roleRepository;
  }

}
