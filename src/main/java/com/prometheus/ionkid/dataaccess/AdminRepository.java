package com.prometheus.ionkid.dataaccess;

import com.prometheus.ionkid.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

  Admin findByUsername(String adminUsername);

}


