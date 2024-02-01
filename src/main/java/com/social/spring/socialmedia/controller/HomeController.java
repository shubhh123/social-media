package com.social.spring.socialmedia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home-controller")
public class HomeController {

    @GetMapping("/test")
    public String homeController() {
        return "Hello user!";
    }
}
