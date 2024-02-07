package com.social.spring.socialmedia.service;

import com.social.spring.socialmedia.model.Comment;
import com.social.spring.socialmedia.model.Post;
import com.social.spring.socialmedia.model.User;
import com.social.spring.socialmedia.repository.CommentRepository;
import com.social.spring.socialmedia.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final PostService postService;
    private final UserService userService;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {

        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);

        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);
        post.getComments().add(savedComment);
        postRepository.save(post);

        return savedComment;
    }

    @Override
    public Comment likedComment(Integer commentId, Integer userId) throws Exception {

        Comment comment =  findCommentById(commentId);
        User user = userService.findUserById(userId);

        if(!comment.getLikedComments().contains(user)) {
            comment.getLikedComments().add(user);
        } else {
            comment.getLikedComments().remove(user);
        }

        return commentRepository.save(comment);
    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception {

        Optional<Comment> optionalComment = commentRepository.findById(commentId);

        if(optionalComment.isEmpty()) {
            throw new Exception("Comment does not exist with that id");
        }

        return optionalComment.get();
    }
}
