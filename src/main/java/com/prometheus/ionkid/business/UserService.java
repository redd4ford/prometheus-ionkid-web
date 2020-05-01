package com.prometheus.ionkid.business;

import com.prometheus.ionkid.dataaccess.UserRepository;
import com.prometheus.ionkid.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username);
  }

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public User getById(Integer id) {
    return userRepository.getOne(id);
  }

  public void create(User user) {
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
