package com.mipt.reddit.mappers;

import com.mipt.reddit.dtos.PostDto;
import com.mipt.reddit.dtos.SignUpDto;
import com.mipt.reddit.dtos.UserDto;
import com.mipt.reddit.model.Post;
import com.mipt.reddit.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostMapper {
    private final UserMapper userMapper;

    public PostDto toPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(userMapper.toUserDto(post.getOwner()))
                .build();
    }
}
