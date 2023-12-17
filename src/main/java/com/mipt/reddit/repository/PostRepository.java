package com.mipt.reddit.repository;

import com.mipt.reddit.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContains(String title);
}