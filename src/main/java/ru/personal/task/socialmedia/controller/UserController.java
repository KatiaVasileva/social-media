package ru.personal.task.socialmedia.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.personal.task.socialmedia.dto.UserDTO;
import ru.personal.task.socialmedia.security.jwt.JwtAuthentication;
import ru.personal.task.socialmedia.service.AuthService;
import ru.personal.task.socialmedia.service.UserService;

@RestController
@RequestMapping("/users")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @GetMapping("/me")
    public UserDTO getUser() {
        final JwtAuthentication authInfo = authService.getAuthInfo();
        return userService.getUser(authInfo);

    }

}
