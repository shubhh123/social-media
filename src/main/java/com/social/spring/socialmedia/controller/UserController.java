package com.social.spring.socialmedia.controller;

import com.social.spring.socialmedia.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-controller")
public class UserController {

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable Integer userId) {
        return new User(userId,"Shubh", "B", "hello@123.com", "12345");
    }
}
