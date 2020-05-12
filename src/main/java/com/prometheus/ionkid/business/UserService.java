package com.prometheus.ionkid.business;

import com.prometheus.ionkid.dataaccess.UserRepository;
import com.prometheus.ionkid.rest.model.Doctor;
import com.prometheus.ionkid.rest.model.Role;
import com.prometheus.ionkid.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private DoctorService doctorService;

  @Override
  public User loadUserByUsername(String googleId) {
    return userRepository.findByGoogleId(googleId);
  }

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public User getById(Integer id) {
    return userRepository.getOne(id);
  }

  public void create(@AuthenticationPrincipal OAuth2User principal) {
    String id = principal.getAttribute("sub");
    User user = loadUserByUsername(id);
    if (user == null) {
      user = new User();
      user.setGoogleId(id);
      user.setFirstName(principal.getAttribute("given_name"));
      user.setLastName(principal.getAttribute("family_name"));
      user.setEmail(principal.getAttribute("email"));
      user.setGender(principal.getAttribute("gender"));
      user.setAvatarUrl(principal.getAttribute("picture"));
      user.setRoles(Collections.singleton(Role.DOCTOR));
      Doctor doctor = new Doctor();
      doctor.setId(user.getId());
      doctorService.create(doctor);
    }
      user.setLastVisit(LocalDateTime.now());
      user.setActive(true);
      userRepository.save(user);
  }

  public void createNotOAuth2User(User user) {
    userRepository.save(user);
  }

  public User update(Integer id, User updatedUser) {
    if (userRepository.findById(id).isPresent()) {
      return userRepository.save(updatedUser);
    } else {
      return null;
    }
  }

  public void deleteById(Integer id) {
    if (userRepository.findById(id).isPresent()) {
      userRepository.deleteById(id);
    }
  }
}
