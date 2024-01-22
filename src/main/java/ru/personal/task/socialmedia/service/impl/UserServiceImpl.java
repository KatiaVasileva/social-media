package ru.personal.task.socialmedia.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.personal.task.socialmedia.dto.MyDatabaseUser;
import ru.personal.task.socialmedia.dto.MyDatabaseUserDetails;
import ru.personal.task.socialmedia.dto.UserDTO;
import ru.personal.task.socialmedia.entity.User;
import ru.personal.task.socialmedia.mapper.UserMapper;
import ru.personal.task.socialmedia.repository.UserRepository;
import ru.personal.task.socialmedia.security.jwt.JwtAuthentication;
import ru.personal.task.socialmedia.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public UserDTO getUser(JwtAuthentication authInfo) {
        String username = authInfo.getUsername();
        User user = userRepository.findByUsername(username);
        return userMapper.userToUserDTO(user);
    }

    @Override
    @Transactional
    public void createUser(UserDetails myDatabaseUserDetails) {
        MyDatabaseUser myDatabaseUser = ((MyDatabaseUserDetails) myDatabaseUserDetails).getMyDatabaseUser();
        User user = userMapper.myDatabaseUserToUser(myDatabaseUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public boolean userExists(String username) {
        return userRepository.findByUsername(username) != null;
    }
}
