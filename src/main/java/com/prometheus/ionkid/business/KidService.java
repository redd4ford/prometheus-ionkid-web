package com.prometheus.ionkid.business;

import com.prometheus.ionkid.dataaccess.KidRepository;
import com.prometheus.ionkid.rest.model.Kid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class KidService extends AbstractService<Kid> {
  @Autowired
  private KidRepository kidRepository;

  @Override
  protected JpaRepository<Kid, Integer> getRepository() {
    return kidRepository;
  }

}