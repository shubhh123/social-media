package com.social.spring.socialmedia.repository;

import com.social.spring.socialmedia.model.Chat;
import com.social.spring.socialmedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    public List<Chat> findByUsersUserId(Integer userId);

//    @Query("SELECT c from Chat c WHERE :loggedInUser MEMBER of c.users AND :user MEMBER of c.users")
//    public Chat findChatByUserId(@Param("loggedInUser")User loggedInUser, @Param("user")User user);

    //@Query("SELECT c FROM Chat c WHERE :loggedInUser MEMBER OF c.users AND :user MEMBER OF c.users")
    @Query("SELECT c FROM Chat c WHERE :user MEMBER OF c.users AND :loggedInUser MEMBER OF c.users")
    public Chat findChatByUserUserId(@Param("user") User user, @Param("loggedInUser") User loggedInUser);
}

/*
I have a ManyToMany association between Chat and User.
This means that a Chat can have multiple users, and a
User can be part of multiple chats. The information
about which users are part of a specific chat is stored
in the users list within the Chat entity.
 */