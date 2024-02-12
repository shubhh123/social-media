package com.social.spring.socialmedia.controller;

import com.social.spring.socialmedia.model.Message;
import com.social.spring.socialmedia.model.User;
import com.social.spring.socialmedia.service.MessageService;
import com.social.spring.socialmedia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;

    @PostMapping("/api/messages/chat/{chatId}")
    public Message createMessage(@RequestBody Message reqMessage, @RequestHeader("Authorization") String token, @PathVariable Integer chatId) throws Exception {
        User user = userService.findUserByToken(token);
        return messageService.createMessage(user, chatId, reqMessage);
    }

    @GetMapping("/api/messages/chat/{chatId}")
        public List<Message> findChatMessages(@RequestHeader("Authorization") String token, @PathVariable Integer chatId) throws Exception {
        User user = userService.findUserByToken(token);
        if(user != null) {
            return messageService.findChatsMessages(chatId);
        } else {
            throw new Exception("User is not authenticated");
        }
    }
}
