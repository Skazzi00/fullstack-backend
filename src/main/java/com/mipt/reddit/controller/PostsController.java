package com.mipt.reddit.controller;

import com.mipt.reddit.dtos.PostCreateOrUpdateDto;
import com.mipt.reddit.dtos.PostDto;
import com.mipt.reddit.dtos.UserDto;
import com.mipt.reddit.services.PostService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostsController {
    Logger logger = LoggerFactory.getLogger(PostsController.class);

    private final PostService postService;

    @GetMapping
    ResponseEntity<List<PostDto>> getPosts(@RequestParam(value = "q", defaultValue = "") String query) {
        return ResponseEntity.ok(postService.getAllPosts(query));
    }

    @PostMapping
    ResponseEntity<Void> addPost(@RequestBody PostCreateOrUpdateDto postCreateOrUpdateDto, UsernamePasswordAuthenticationToken principal) {
        UserDto user = (UserDto) principal.getPrincipal();
        postService.createPost(postCreateOrUpdateDto, user.getUsername());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> updatePost(
            @PathVariable(name = "id") long id,
            @RequestBody PostCreateOrUpdateDto postUpdateDto,
            UsernamePasswordAuthenticationToken principal)
    {
        UserDto user = (UserDto) principal.getPrincipal();
        postService.updatePost(id, postUpdateDto, user.getUsername());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePost(@PathVariable(name = "id") long id, UsernamePasswordAuthenticationToken principal) {
        UserDto user = (UserDto) principal.getPrincipal();
        postService.deletePost(id, user.getUsername());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    ResponseEntity<PostDto> getPost(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }
}
