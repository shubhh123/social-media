package com.social.spring.socialmedia.service;

import com.social.spring.socialmedia.exceptions.UserNotFoundException;
import com.social.spring.socialmedia.model.User;

import java.util.List;

public interface UserService {

    public List<User> findAllUsers();
    public User registerUser(User user);
    public User findUserById(Integer userId) throws UserNotFoundException;
    public User findUserByEmail(String email);
    public User followUser(Integer loggedInUser, Integer userId2);
    public User updateUser(User user, Integer userId) throws Exception;
    public List<User> searchUser(String query);
    public User findUserByToken(String token);

}
