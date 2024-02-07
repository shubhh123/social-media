package com.social.spring.socialmedia.service;

import com.social.spring.socialmedia.model.Post;
import com.social.spring.socialmedia.model.User;
import com.social.spring.socialmedia.repository.PostRepository;
import com.social.spring.socialmedia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    private final UserService userService;

    private  final UserRepository userRepository;


    @Override
    public Post createPost(Post post, Integer userId) {

        LocalDateTime now = LocalDateTime.now(); // Gets current date and time

        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setCreatedTime(now);
        newPost.setVideo(post.getVideo());
        newPost.setUser(userService.findUserById(userId));
        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        //Check if the user who created the post with the user id obtained above, both are same.
        if(post.getUser().getUserId() != user.getUserId()) {
            throw  new Exception("You cannot delete other's post");
        }

        postRepository.delete(post);
        return "Post deleted successfully";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {
        return postRepository.findPostByUserId(userId);

    }

    @Override
    public Post findPostById(Integer postId) throws Exception {

        Optional<Post> post = postRepository.findById(postId);

        if(post.isEmpty()) {
            throw new Exception("Post not found with post id: "+ postId);
        }
        return post.get();
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        System.out.println("Post caption"+post.getCaption());
        System.out.println("User First name: "+ user.getFirstName());

        if(user.getSavedPosts().contains(post)) {
            System.out.println("Post was already saved... removing saved post");
            user.getSavedPosts().remove(post);
            System.out.println("Post removed from saved list");
        }

        else {
            System.out.println("Saving...");
            user.getSavedPosts().add(post);

            System.out.println("Post saved!");
        }
        userRepository.save(user);
        //userService.updateUser(user, userId);
        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        //Like/Unlike a post[Toggle]
        if(post.getLikedPostByAUser().contains(user)) {
            post.getLikedPostByAUser().remove(user);
        } else {
            post.getLikedPostByAUser().add(user);
        }

        return postRepository.save(post);
    }
}
