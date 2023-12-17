package com.mipt.reddit.controller;

import com.mipt.reddit.configs.UserAuthenticationProvider;
import com.mipt.reddit.dtos.CredentialsDto;
import com.mipt.reddit.dtos.SignUpDto;
import com.mipt.reddit.dtos.UserDto;
import com.mipt.reddit.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) {
        UserDto userDto = userService.login(credentialsDto);
        userDto.setToken(userAuthenticationProvider.createToken(userDto));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto user) {
        UserDto createdUser = userService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(createdUser));
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/user")
    public ResponseEntity<UserDto> user(UsernamePasswordAuthenticationToken principal) {
        UserDto user = (UserDto) principal.getPrincipal();
        UserDto byLogin = userService.findByLogin(user.getUsername());
        return ResponseEntity.ok(byLogin);
    }

}