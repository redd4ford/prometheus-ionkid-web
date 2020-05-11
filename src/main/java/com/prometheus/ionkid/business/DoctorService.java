package com.prometheus.ionkid.business;

import com.prometheus.ionkid.dataaccess.DoctorRepository;
import com.prometheus.ionkid.rest.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorService extends AbstractService<Doctor> {
  @Autowired
  private DoctorRepository doctorRepository;

  @Override
  protected JpaRepository<Doctor, Integer> getRepository() {
    return doctorRepository;
  }

}