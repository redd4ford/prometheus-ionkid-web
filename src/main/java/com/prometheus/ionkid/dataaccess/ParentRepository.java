package com.prometheus.ionkid.dataaccess;

import com.prometheus.ionkid.rest.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Integer> {


}