package com.prometheus.ionkid.dataaccess;

import com.prometheus.ionkid.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

  List<Doctor> findByName(String name);

  Doctor getOne(Integer doctorId);

}
