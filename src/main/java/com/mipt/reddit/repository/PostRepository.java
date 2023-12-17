package com.mipt.reddit.repository;

import com.mipt.reddit.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {

}