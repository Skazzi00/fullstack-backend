package com.mipt.reddit.dtos;

import lombok.Data;

@Data
public class PostUpdateDto {
    long id;
    String title;
    String content;
}
