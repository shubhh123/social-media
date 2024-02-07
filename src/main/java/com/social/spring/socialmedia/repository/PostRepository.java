package com.social.spring.socialmedia.repository;

import com.social.spring.socialmedia.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("Select p from Post p where p.user.userId=:userId") //Fetching all post for a particular user
    List<Post> findPostByUserId(Integer userId);
}
