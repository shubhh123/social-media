package com.social.spring.socialmedia.service;

import com.social.spring.socialmedia.config.JwtProvider;
import com.social.spring.socialmedia.model.User;
import com.social.spring.socialmedia.repository.UserRepository;
import com.social.spring.socialmedia.request.LoginRequest;
import com.social.spring.socialmedia.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final CustomUserDetailService customUserDetailService;

    @Override
    public AuthResponse registerUser(User user) throws Exception {
        Optional<User> isUserExist = userRepository.findByEmail(user.getEmail());

        if(isUserExist.isPresent()) {
            throw new Exception("Email Already in use by other account");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword()); //user = principal, password=credentials

        System.out.println(" ");
        System.out.println("PRINCIPLES: "+authentication.getPrincipal());
        System.out.println("CREDENTIALS: "+authentication.getCredentials());
        System.out.println(" ");


        String token = JwtProvider.generateToken(authentication);

        System.out.println("JWT TOKEN: "+token);

        AuthResponse response = new AuthResponse(token, "Registration successful");

        userRepository.save(user);
        //return userRepository.save(user);
        return response;
    }

    @Override
    public AuthResponse signIn(LoginRequest loginRequest) {
        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication);
        AuthResponse response = new AuthResponse(token, "Login successful");
        //return userRepository.save(user);
        return response;
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(email);

        if(userDetails == null) {
            throw new BadCredentialsException("Invalid username");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())) {//userDetails.getPassword() has the encoded password.
            throw new BadCredentialsException("The password is incorrect");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
    }
}
