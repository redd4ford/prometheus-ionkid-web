package com.prometheus.ionkid.dataaccess;

import com.prometheus.ionkid.domain.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Integer> {

  Parent findByUsername(String username);

}