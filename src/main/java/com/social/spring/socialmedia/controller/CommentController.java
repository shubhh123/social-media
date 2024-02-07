package com.social.spring.socialmedia.controller;

import com.social.spring.socialmedia.model.Comment;
import com.social.spring.socialmedia.model.User;
import com.social.spring.socialmedia.service.CommentService;
import com.social.spring.socialmedia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @PostMapping("/api/comment/post/{postId}")
    public Comment createComment(@RequestBody Comment comment, @RequestHeader("Authorization") String token, @PathVariable Integer postId) throws Exception {
        User loggedInUser = userService.findUserByToken(token);
        return commentService.createComment(comment, postId, loggedInUser.getUserId());
    }

    @PutMapping("/api/comment/like/{commentId}")
    public Comment likeAComment(@RequestHeader("Authorization") String token, @PathVariable Integer commentId) throws Exception {
        User loggedInUser = userService.findUserByToken(token);
        return commentService.likedComment(commentId, loggedInUser.getUserId());
    }

    @GetMapping("/api/comment/{commentId}")
    public Comment findCommentById(@RequestHeader("Authorization") String token, @PathVariable Integer commentId) throws Exception {
        User loggedInUser = userService.findUserByToken(token);
        if(loggedInUser != null) {
            return commentService.findCommentById(commentId);
        } else {
            throw new Exception("Not Authorized");
        }
    }
}
