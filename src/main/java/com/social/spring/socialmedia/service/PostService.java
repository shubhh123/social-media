package com.social.spring.socialmedia.service;

import com.social.spring.socialmedia.exceptions.PostNotFoundException;
import com.social.spring.socialmedia.exceptions.UserException;
import com.social.spring.socialmedia.model.Post;

import java.util.List;

public interface PostService {
    Post createPost(Post post, Integer userId) throws UserException;

    String deletePost(Integer postId, Integer userId) throws Exception;
    List<Post> findPostByUserId(Integer userId);

    Post findPostById(Integer postId) throws PostNotFoundException;

    List<Post> findAllPost();

    Post savePost(Integer postId, Integer userId) throws Exception; //save user interested posts

    Post likePost(Integer postId, Integer userId) throws Exception;
}
