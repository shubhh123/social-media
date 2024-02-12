package com.social.spring.socialmedia.repository;

import com.social.spring.socialmedia.model.Reel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReelRepository extends JpaRepository<Reel, Long> {

    public List<Reel> findByUserUserId(Integer userId);
}
