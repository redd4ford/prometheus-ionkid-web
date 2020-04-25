package com.prometheus.ionkid.business;

import com.prometheus.ionkid.dataaccess.UserRepository;
import com.prometheus.ionkid.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserService implements OAuth2UserService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
    return userRepository.findByUsername(oAuth2UserRequest.getClientRegistration().getClientName());
  }
}
