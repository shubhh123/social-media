package com.social.spring.socialmedia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reelId;

    private String title;

    private String reel;

    @ManyToOne
    private User user;

}
