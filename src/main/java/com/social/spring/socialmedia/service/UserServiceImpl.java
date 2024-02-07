package com.social.spring.socialmedia.service;

import com.social.spring.socialmedia.config.JwtProvider;
import com.social.spring.socialmedia.exceptions.UserNotFoundException;
import com.social.spring.socialmedia.model.User;
import com.social.spring.socialmedia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Integer userId) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElseThrow(() -> new UserNotFoundException("No user found with ID: " + userId));
    }


    @Override
    public User findUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElseThrow(() -> new UserNotFoundException("No user found form email: " + email));
    }

    @Override
    public User followUser(Integer loggedInUserId, Integer userId2) {

        User loggedInUser = findUserById(loggedInUserId);
        User user2 = findUserById(userId2);

        //loggedInUser WANTS TO FOLLOW USER2

        //Add Id of User1 to followers list of User2
        user2.getFollowers().add(loggedInUser.getUserId());

        //Add id of User2 to the following list of User1
        loggedInUser.getFollowing().add(user2.getUserId());

        userRepository.save(loggedInUser);
        userRepository.save(user2);

        //as loggedInUser wanted to follow user2, return loggedInUser.
        return loggedInUser;
    }

    @Override
    public User updateUser(User user, Integer userId) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            if(user.getFirstName() != null) {
                existingUser.setFirstName(user.getFirstName());
            }
            if(user.getLastName() != null) {
                existingUser.setLastName(user.getLastName());
            }
            if(user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }
            if(user.getPassword() != null) {
                existingUser.setPassword(user.getPassword());
            }
            if(user.getGender() != null) {
                existingUser.setGender(user.getGender());
            }

//            existingUser.setFirstName(user.getFirstName());
//            existingUser.setLastName(user.getLastName());
//            existingUser.setEmail(user.getEmail());
//            existingUser.setPassword(user.getPassword());
//            existingUser.setGender(user.getGender());
//            if(existingUser.getSavedPosts() != null) {
//                System.out.println("From updateUser of UserServiceImpl, getting: "+user.getSavedPosts()+" and saving...");
//                existingUser.setSavedPosts(user.getSavedPosts());
//
//               List<User> userList = new ArrayList<>();
//
//               for(Post posts: existingUser.getSavedPosts()) {
//                  userList.add(posts.getUser());
//               }
//
//
//            }

            return userRepository.save(existingUser);
        }
        else {
            throw new UserNotFoundException("User "+ user.getFirstName()+" does not exist");
        }
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public User findUserByToken(String token) {
        String email = JwtProvider.getEmailFromJwtToken(token);
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent()) {
            return user.get();
        }
        else {
            throw new UserNotFoundException("User Not dound with token");
        }
    }
}
