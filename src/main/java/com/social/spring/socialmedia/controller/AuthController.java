package com.social.spring.socialmedia.controller;

import com.social.spring.socialmedia.model.User;
import com.social.spring.socialmedia.request.LoginRequest;
import com.social.spring.socialmedia.response.AuthResponse;
import com.social.spring.socialmedia.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    @PostMapping("/signup")
    public AuthResponse registerUser(@RequestBody User user) throws Exception {
        return authService.registerUser(user);
    }

    @PostMapping("/signin")
    public AuthResponse signIn(@RequestBody LoginRequest loginRequest) {
        return authService.signIn(loginRequest);
    }
}
