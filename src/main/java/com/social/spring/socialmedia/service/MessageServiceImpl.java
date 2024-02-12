package com.social.spring.socialmedia.service;

import com.social.spring.socialmedia.model.Chat;
import com.social.spring.socialmedia.model.Message;
import com.social.spring.socialmedia.model.User;
import com.social.spring.socialmedia.repository.ChatRepository;
import com.social.spring.socialmedia.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepository;

    private final ChatService chatService;

    private final ChatRepository chatRepository;

    @Override
    public Message createMessage(User user, Integer chatId, Message req) throws Exception {

        Chat chat = chatService.findChatById(chatId);

        Message message = new Message();
        message.setChat(chat);
        message.setMessageContent(req.getMessageContent());
        message.setImage(req.getImage());
        message.setUser(user);
        message.setTimestamp(LocalDateTime.now());

        chat.getMessages().add(message);

        chatRepository.save(chat);

        return messageRepository.save(message);
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) throws Exception {
        //Chat chat = chatService.findChatById(chatId);

        return messageRepository.findByChatChatId(chatId);
    }
}
