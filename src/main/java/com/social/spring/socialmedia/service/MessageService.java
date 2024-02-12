package com.social.spring.socialmedia.service;

import com.social.spring.socialmedia.model.Message;
import com.social.spring.socialmedia.model.User;

import java.util.List;

public interface MessageService {

    public Message createMessage(User user, Integer chatId, Message req) throws Exception;
    public List<Message> findChatsMessages(Integer chatId) throws Exception;


}
