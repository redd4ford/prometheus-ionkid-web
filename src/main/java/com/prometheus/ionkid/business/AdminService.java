package com.prometheus.ionkid.business;

import com.prometheus.ionkid.dataaccess.AdminRepository;
import com.prometheus.ionkid.rest.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends AbstractService<Admin> {

  @Autowired
  private AdminRepository adminRepository;

  @Override
  protected JpaRepository<Admin, Integer> getRepository() {
    return adminRepository;
  }

  public Admin loadByEmail(String email) {
    return adminRepository.findByEmail(email);
  }

}