package com.mipt.reddit.dtos;

import lombok.Data;

@Data
public class PostCreateOrUpdateDto {
    String title;
    String content;
}
