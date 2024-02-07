package com.social.spring.socialmedia.service;

import com.social.spring.socialmedia.model.Comment;

public interface CommentService {

    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception;
    public Comment likedComment(Integer commentId, Integer userId) throws Exception;
    public Comment findCommentById(Integer commentId) throws Exception;

}
