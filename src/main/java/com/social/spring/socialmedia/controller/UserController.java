package com.social.spring.socialmedia.controller;

import com.social.spring.socialmedia.exceptions.UserException;
import com.social.spring.socialmedia.model.User;
import com.social.spring.socialmedia.repository.UserRepository;
import com.social.spring.socialmedia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    @PostMapping("user/save-user")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/api/users")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/api/user/{userId}")
    public User findUserById(@PathVariable Integer userId) throws UserException {
        return userService.findUserById(userId);
    }

    @GetMapping("/api/user/find-by-email")
    public User findUserByEmail(@RequestParam String email) throws UserException {
        return userService.findUserByEmail(email);
    }

    ///api/user/{userId}
    @PutMapping("/api/user/update")
    public User updateUser(@RequestHeader("Authorization")String token, @RequestBody User user) throws UserException {
        User loggedInUser = userService.findUserByToken(token);
        return userService.updateUser(user, loggedInUser.getUserId());
    }

    @PutMapping("/user/follow/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization")String token, @PathVariable Integer userId2) throws UserException {
        User loggedInUser = userService.findUserByToken(token);

        //USER1 FOLLOWS USER2
        return userService.followUser(loggedInUser.getUserId(), userId2);
    }

    @GetMapping("/api/user/search") //NOT WORKING
    public List<User> searchUser(@RequestParam("query")String query) {
        return userService.searchUser(query);
    }

    @GetMapping("/api/user/profile")
    public User getUserFromToken(@RequestHeader("Authorization")String token) throws UserException {
        User user = userService.findUserByToken(token);
        user.setPassword(null);

        return user;
    }
}




















//    @DeleteMapping("/user/{userId}")
//    public String deleteUser(@PathVariable Integer userId) {
//        Optional<User> optionalUser = repository.findById(userId);
//
//        if(optionalUser.isPresent()) {
//            repository.delete(optionalUser.get());
//            return "User deleted successfully";
//        } else {
//             return "no user found";
//        }
//    }


