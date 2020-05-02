package com.prometheus.ionkid.config;

import com.prometheus.ionkid.dataaccess.DoctorRepository;
import com.prometheus.ionkid.dataaccess.UserRepository;
import com.prometheus.ionkid.rest.model.Doctor;
import com.prometheus.ionkid.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import java.time.LocalDateTime;
import java.util.Map;

public class GooglePrincipalExtractor implements PrincipalExtractor {

  @Bean
  public PrincipalExtractor principalExtractor(@Autowired UserRepository userRepository,
                                               @Autowired DoctorRepository doctorRepository) {
    return map -> {
      String id = (String) map.get("id");
      User user = userRepository.findByGoogleId(id);
      if (user == null) {
        user = new User();
        user.setGoogleId(id);
        user.setFirstName((String) map.get("given_name"));
        user.setLastName((String) map.get("family_name"));
        user.setEmail((String) map.get("email"));
        user.setGender((String) map.get("gender"));
        user.setAvatarUrl((String) map.get("picture"));
        Doctor doctor = new Doctor();
        doctor.setId(user.getId());
        doctorRepository.save(doctor);
      }
      user.setLastVisit(LocalDateTime.now());
      user.setActive(true);
      userRepository.save(user);
      return user;
    };
  }

  @Override
  public Object extractPrincipal(Map<String, Object> map) {
    return map.get("given_name");
  }

}