package com.social.spring.socialmedia.service;

import com.social.spring.socialmedia.exceptions.ReelNotFoundForUserException;
import com.social.spring.socialmedia.exceptions.UserException;
import com.social.spring.socialmedia.model.Reel;
import com.social.spring.socialmedia.model.User;

import java.util.List;

public interface ReelService {

    public Reel createReel(Reel reel, User user);
    public List<Reel> findAllReel();
    public List<Reel> findUserCreatedReel(Integer userId) throws ReelNotFoundForUserException, UserException;
}
