package com.social.spring.socialmedia.service;

import com.social.spring.socialmedia.exceptions.ChatNotFoundException;
import com.social.spring.socialmedia.model.Chat;
import com.social.spring.socialmedia.model.User;
import com.social.spring.socialmedia.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ChatServiceImpl implements ChatService{

    private final ChatRepository chatRepository;

    @Override
    public Chat createChat(User loggedInUser, User user2) {

        Chat isChatExist = chatRepository.findChatByUserUserId(user2, loggedInUser); //findChatByUserUserId method to check if two users are already associated with the same chat before
        if(isChatExist != null) {
            return isChatExist;
        }

        Chat chat = new Chat();
        chat.getUsers().add(user2);
        chat.getUsers().add(loggedInUser);
        chat.setTimeStamp(LocalDateTime.now());

        return chatRepository.save(chat);
    }

    @Override
    public Chat findChatById(Integer chatId) throws ChatNotFoundException {

        Optional<Chat> isChatExist = chatRepository.findById(chatId);

        if(isChatExist.isPresent()) {
            return isChatExist.get();
        } else {
            throw new ChatNotFoundException("Chat not found for the requested ID: "+chatId);
        }
    }

    @Override
    public List<Chat> findUsersChat(Integer userId) {
        return chatRepository.findByUsersUserId(userId);
    }
}
