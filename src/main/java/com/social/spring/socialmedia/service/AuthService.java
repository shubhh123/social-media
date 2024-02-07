package com.social.spring.socialmedia.service;

import com.social.spring.socialmedia.model.User;
import com.social.spring.socialmedia.request.LoginRequest;
import com.social.spring.socialmedia.response.AuthResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {
    public AuthResponse registerUser(@RequestBody User user) throws Exception;
    public AuthResponse signIn(@RequestBody LoginRequest loginRequest);
}
