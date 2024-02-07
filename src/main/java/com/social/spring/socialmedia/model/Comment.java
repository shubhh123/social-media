package com.social.spring.socialmedia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer commentId;

    private String content;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<User> likedComments;

    private LocalDateTime createdAt;
}
