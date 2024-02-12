package com.social.spring.socialmedia.service;

import com.social.spring.socialmedia.exceptions.ReelNotFoundForUserException;
import com.social.spring.socialmedia.exceptions.UserException;
import com.social.spring.socialmedia.model.Reel;
import com.social.spring.socialmedia.model.User;
import com.social.spring.socialmedia.repository.ReelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReelServiceImpl implements ReelService{

    private final ReelRepository reelRepository;
    private final UserService userService;
    @Override
    public Reel createReel(Reel reel, User user) {

        Reel userReel = new Reel();
        userReel.setTitle(reel.getTitle());
        userReel.setReel(reel.getReel());
        userReel.setUser(user);

        return reelRepository.save(userReel);
    }

    @Override
    public List<Reel> findAllReel() {
        return reelRepository.findAll();
    }

    @Override
    public List<Reel> findUserCreatedReel(Integer userId) throws ReelNotFoundForUserException, UserException {

        User user = userService.findUserById(userId);
        if(user != null) {
            return reelRepository.findByUserUserId(userId);
        }
        else {
            throw new ReelNotFoundForUserException("Reel failure! User does not exist for the given ID");
        }
    }
}
