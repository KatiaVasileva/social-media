package ru.personal.task.socialmedia.controller;

import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.personal.task.socialmedia.dto.Register;
import ru.personal.task.socialmedia.security.jwt.JwtRequest;
import ru.personal.task.socialmedia.security.jwt.JwtResponse;
import ru.personal.task.socialmedia.security.jwt.RefreshJwtRequest;
import ru.personal.task.socialmedia.service.AuthService;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest authRequest) throws AuthException {
        return authService.login(authRequest);
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody Register register) {
        authService.register(register);
    }

    @PostMapping("/token")
    public JwtResponse getNewAccessToken(@RequestBody RefreshJwtRequest request) throws AuthException {
        return authService.getAccessToken(request.getRefreshToken());
    }

    @PostMapping("/refresh")
    public JwtResponse getNewRefreshToken(@RequestBody RefreshJwtRequest request) throws AuthException {
        return authService.refresh(request.getRefreshToken());
    }
}
