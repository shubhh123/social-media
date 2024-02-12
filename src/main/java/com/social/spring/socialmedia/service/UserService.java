package com.social.spring.socialmedia.service;

import com.social.spring.socialmedia.exceptions.UserException;
import com.social.spring.socialmedia.model.User;

import java.util.List;

public interface UserService {

    public List<User> findAllUsers();
    public User registerUser(User user);


    public User findUserById(Integer userId) throws UserException;
    public User findUserByEmail(String email) throws UserException;
    public User followUser(Integer loggedInUser, Integer userId2) throws UserException;
    public User updateUser(User user, Integer userId) throws UserException;
    public List<User> searchUser(String query);
    public User findUserByToken(String token) throws UserException;

}
