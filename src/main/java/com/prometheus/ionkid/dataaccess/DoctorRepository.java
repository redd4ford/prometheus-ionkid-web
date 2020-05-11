package com.prometheus.ionkid.dataaccess;

import com.prometheus.ionkid.rest.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {


  Doctor getOne(Integer doctorId);

}
