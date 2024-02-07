package com.social.spring.socialmedia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;
    private String gender;
    private List<Integer> followers = new ArrayList<>();
    private List<Integer> following = new ArrayList<>();

    @ManyToMany
    @JsonIgnore   // Add this annotation to break the loop
//    @ToString.Exclude
    private List<Post> savedPosts = new ArrayList<>(); //A USER CAN SAVE MULTIPLE POSTS
}
