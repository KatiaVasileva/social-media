package ru.personal.task.socialmedia.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.personal.task.socialmedia.dto.UserDTO;
import ru.personal.task.socialmedia.security.jwt.JwtAuthentication;

public interface UserService {

    UserDTO getUser(JwtAuthentication authInfo);

    void createUser(UserDetails myDatabaseUserDetails);

    boolean userExists(String username);

}
