package com.mipt.reddit.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PostDto {
    long id;
    String title;
    String content;
    UserDto author;
}
