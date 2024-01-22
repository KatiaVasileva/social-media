package ru.personal.task.socialmedia.service.impl;

import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.personal.task.socialmedia.dto.MyDatabaseUserDetails;
import ru.personal.task.socialmedia.dto.Register;
import ru.personal.task.socialmedia.entity.User;
import ru.personal.task.socialmedia.mapper.UserMapper;
import ru.personal.task.socialmedia.repository.UserRepository;
import ru.personal.task.socialmedia.security.jwt.JwtAuthentication;
import ru.personal.task.socialmedia.security.jwt.JwtProvider;
import ru.personal.task.socialmedia.security.jwt.JwtRequest;
import ru.personal.task.socialmedia.security.jwt.JwtResponse;
import ru.personal.task.socialmedia.service.AuthService;
import ru.personal.task.socialmedia.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserService userService;

    @Override
    public JwtResponse login(@NonNull JwtRequest authRequest) throws AuthException {
        final User user = userRepository.findByUsername(authRequest.getUsername());
        if (user == null) {
            throw new AuthException("User is not found");
        }
        if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getUsername(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Invalid password");
        }
    }

    @Override
    public void register(Register register) {
        User user = userMapper.registerToUser(register);
        MyDatabaseUserDetails myDatabaseUserDetails =
                new MyDatabaseUserDetails(userMapper.userToMyDatabaseUser(user));
        if (! userService.userExists(register.getUsername())) {
            userService.createUser(myDatabaseUserDetails);
        }
    }

    @Override
    public JwtResponse getAccessToken(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateAccessToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String username = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(username);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userRepository.findByUsername(username);
                if (user == null) {
                    throw new AuthException("User is not found");
                }
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    @Override
    public JwtResponse refresh(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateAccessToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String username = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(username);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userRepository.findByUsername(username);
                if (user == null) {
                    throw new AuthException("User is not found");
                }
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getUsername(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Invalid JWT");
    }

    @Override
    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

}
