package com.mipt.reddit.services;

import com.mipt.reddit.dtos.PostDto;
import com.mipt.reddit.mappers.PostMapper;
import com.mipt.reddit.model.Post;
import com.mipt.reddit.model.User;
import com.mipt.reddit.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.mipt.reddit.dtos.PostCreateOrUpdateDto;
import com.mipt.reddit.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;

    public List<PostDto> getAllPosts(String query) {
        List<Post> allPosts = postRepository.findByTitleContains(query);
        return allPosts.stream().map(postMapper::toPostDto).toList();
    }

    private void checkPermission(User user, Post post) {
        if (post.getOwner().getId() != user.getId()) {
            throw new AccessDeniedException("User is not the owner of the post");
        }
    }

    public void createPost(PostCreateOrUpdateDto postCreateOrUpdateDto, String username) {
        var user = userRepository.findByUsername(username).orElseThrow();
        
        Post postEntity = Post.builder()
                .title(postCreateOrUpdateDto.getTitle())
                .content(postCreateOrUpdateDto.getContent())
                .owner(user)
                .build();

        postRepository.save(postEntity);
    }

     public void updatePost(long id, PostCreateOrUpdateDto postUpdateDto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Post post = postRepository.findById(id).orElseThrow();
        
        checkPermission(user, post);

        post.setTitle(postUpdateDto.getTitle());
        post.setContent(postUpdateDto.getContent());
        postRepository.save(post);
    }

    public void deletePost(long id, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Post post = postRepository.findById(id).orElseThrow();

        checkPermission(user, post);

        postRepository.delete(post);
    }

    public PostDto getPost(long id) {
        Post post = postRepository.findById(id).orElseThrow();
        return postMapper.toPostDto(post);
    }


}
