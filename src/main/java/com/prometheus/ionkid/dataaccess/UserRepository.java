package com.prometheus.ionkid.dataaccess;

import com.prometheus.ionkid.rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  User findByGoogleId(String id);

  User findByEmail(String email);
}
