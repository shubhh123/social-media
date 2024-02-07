package com.social.spring.socialmedia.service;

import com.social.spring.socialmedia.model.User;
import com.social.spring.socialmedia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
* CustomUserDetailService class, by implementing UserDetailsService,
* serves as an "AUTHENTICATION PROVIDER" that loads user details based
* on the provided username (email in your case).
* */
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        List<GrantedAuthority> authorities = new ArrayList<>();

        if(user.isEmpty()) {
            throw new UsernameNotFoundException("The username/email field was not found!");
        }
        return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(),authorities);
    }
}
