package com.mipt.reddit.mappers;


import com.mipt.reddit.dtos.SignUpDto;
import com.mipt.reddit.dtos.UserDto;
import com.mipt.reddit.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toUserDto(User user) {
        return UserDto.builder().username(user.getUsername()).id(user.getId()).build();
    }

    public User signUpToUser(SignUpDto signUpDto) {
        return User.builder().username(signUpDto.getUsername()).build();
    }
}