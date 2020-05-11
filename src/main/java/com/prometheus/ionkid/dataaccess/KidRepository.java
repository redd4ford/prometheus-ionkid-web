package com.prometheus.ionkid.dataaccess;

import com.prometheus.ionkid.rest.model.Kid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KidRepository extends JpaRepository<Kid, Integer> {


}
