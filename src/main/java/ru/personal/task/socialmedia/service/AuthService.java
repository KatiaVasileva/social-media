package ru.personal.task.socialmedia.service;

import jakarta.security.auth.message.AuthException;
import ru.personal.task.socialmedia.dto.Register;
import ru.personal.task.socialmedia.security.jwt.JwtAuthentication;
import ru.personal.task.socialmedia.security.jwt.JwtRequest;
import ru.personal.task.socialmedia.security.jwt.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest authRequest) throws AuthException;

    void register(Register register);

    JwtResponse getAccessToken(String request) throws AuthException;

    JwtResponse refresh(String request) throws AuthException;

    JwtAuthentication getAuthInfo();

}
