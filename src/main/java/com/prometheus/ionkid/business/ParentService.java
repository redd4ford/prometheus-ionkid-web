package com.prometheus.ionkid.business;

import com.prometheus.ionkid.dataaccess.ParentRepository;
import com.prometheus.ionkid.rest.model.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ParentService extends AbstractService<Parent> {

  @Autowired
  private ParentRepository parentRepository;

  @Override
  protected JpaRepository<Parent, Integer> getRepository() {
    return parentRepository;
  }

}