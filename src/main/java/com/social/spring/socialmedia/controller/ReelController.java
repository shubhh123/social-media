package com.social.spring.socialmedia.controller;

import com.social.spring.socialmedia.exceptions.UserException;
import com.social.spring.socialmedia.model.Reel;
import com.social.spring.socialmedia.model.User;
import com.social.spring.socialmedia.service.ReelService;
import com.social.spring.socialmedia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReelController {
    private final ReelService reelService;
    private final UserService userService;

    @PostMapping("/api/reel")
    public Reel createReel(@RequestBody Reel reel, @RequestHeader("Authorization") String token) throws UserException {
        User loggedInUser = userService.findUserByToken(token);
        return reelService.createReel(reel, loggedInUser);
    }

    @GetMapping("/api/reel")
    public List<Reel> findAllReel() {
        return reelService.findAllReel();
    }

    @GetMapping("/api/reel/{userId}")
    public List<Reel> findUserCreatedReel(@PathVariable Integer userId) throws Exception {
        return reelService.findUserCreatedReel(userId);
    }
}
