package com.social.spring.socialmedia.service;

import com.social.spring.socialmedia.exceptions.ChatNotFoundException;
import com.social.spring.socialmedia.model.Chat;
import com.social.spring.socialmedia.model.User;

import java.util.List;

public interface ChatService {

    public Chat createChat(User loggedInUser, User user2);
    public Chat findChatById(Integer chatId) throws ChatNotFoundException;
    public List<Chat> findUsersChat(Integer userId);
}
