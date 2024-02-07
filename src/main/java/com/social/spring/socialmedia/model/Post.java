package com.social.spring.socialmedia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postId;

    private String caption;
    private String image;
    private String video;

    @ManyToOne
    private User user;
    private LocalDateTime createdTime;

    //@ManyToMany
    @OneToMany    //Many users can like only a single post [@OneToMany]
    private List<User> likedPostByAUser = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
//    @JoinTable(
//            name = "user_saved_posts",
//            joinColumns = @JoinColumn(name = "user_user_id"),  // Specify the name of the foreign key column for User
//            inverseJoinColumns = @JoinColumn(name = "saved_posts_post_id"))
    private List<Post> usersWhoSavedThisPost = new ArrayList<>();
<<<<<<< HEAD

    @OneToMany
    private List<Comment> comments = new ArrayList<>();
=======
>>>>>>> origin/main
}
