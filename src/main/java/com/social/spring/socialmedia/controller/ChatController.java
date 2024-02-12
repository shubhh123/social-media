package com.social.spring.socialmedia.controller;

import com.social.spring.socialmedia.exceptions.UserException;
import com.social.spring.socialmedia.model.Chat;
import com.social.spring.socialmedia.model.User;
import com.social.spring.socialmedia.request.CreateChatRequest;
import com.social.spring.socialmedia.service.ChatService;
import com.social.spring.socialmedia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;

    @PostMapping("/api/chat")
    public Chat createChat(@RequestHeader("Authorization") String token, @RequestBody CreateChatRequest chatRequest) throws UserException {
        User loggedinUser = userService.findUserByToken(token);
        User user2 = userService.findUserById(chatRequest.getInitiateChatWithUserId());

        return chatService.createChat(loggedinUser, user2);
    }

    @GetMapping("/api/chat")
    public List<Chat> findUsersChat(@RequestHeader("Authorization") String token) throws UserException {
        User loggedinUser = userService.findUserByToken(token);
        return chatService.findUsersChat(loggedinUser.getUserId());
    }
}
