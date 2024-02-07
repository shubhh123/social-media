package com.social.spring.socialmedia.controller;

import com.social.spring.socialmedia.model.Post;
import com.social.spring.socialmedia.model.User;
import com.social.spring.socialmedia.response.ApiResponse;
import com.social.spring.socialmedia.service.PostService;
import com.social.spring.socialmedia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final UserService userService;
    @PostMapping("/api/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @RequestHeader("Authorization")String token){  //ResponseEntity allows you to send status code as the response too..

        User loggedInUser = userService.findUserByToken(token);

        Post createdPost = postService.createPost(post, loggedInUser.getUserId());
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @DeleteMapping("api/posts/{postId}") //WHEN POST SAVED, GIVING PROBLEM WHILE DELETING.
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @RequestHeader("Authorization")String token) throws Exception {

        User loggedInUser = userService.findUserByToken(token);

        String message = postService.deletePost(postId, loggedInUser.getUserId());
        ApiResponse response = new ApiResponse(message, true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{postId}") //NOT WORKING
    public ResponseEntity<Post> findPostById(@PathVariable Integer postId) throws Exception {
        return new ResponseEntity<>(postService.findPostById(postId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> findUserPosts(@PathVariable Integer userId) {
        return new ResponseEntity<>(postService.findPostByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>> findAllPosts() { //NOT WORKING
        return new ResponseEntity<>(postService.findAllPost(), HttpStatus.OK);
    }

    @PutMapping("api/posts/{postId}/save")
    public ResponseEntity<Post> saveUserInterestedPost(@PathVariable Integer postId, @RequestHeader("Authorization")String token) throws Exception {
        User loggedInUser = userService.findUserByToken(token);
        return new ResponseEntity<>(postService.savePost(postId, loggedInUser.getUserId()), HttpStatus.OK);
    }

<<<<<<< HEAD
    @PutMapping("/{postId}/like")
=======
    @PutMapping("/api/posts/{postId}/like")
>>>>>>> origin/main
    public ResponseEntity<Post> likePost(@PathVariable Integer postId, @RequestHeader("Authorization")String token) throws Exception {
        User loggedInUser = userService.findUserByToken(token);
        return new ResponseEntity<>(postService.likePost(postId, loggedInUser.getUserId()), HttpStatus.OK);
    }
}
