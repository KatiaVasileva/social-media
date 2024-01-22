package ru.personal.task.socialmedia.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.personal.task.socialmedia.dto.MyDatabaseUser;
import ru.personal.task.socialmedia.dto.Register;
import ru.personal.task.socialmedia.dto.UserDTO;
import ru.personal.task.socialmedia.entity.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class UserMapper {

    public abstract User registerToUser(Register register);

    public abstract User myDatabaseUserToUser(MyDatabaseUser myDatabaseUser);

    public abstract MyDatabaseUser userToMyDatabaseUser(User user);

    public abstract UserDTO userToUserDTO(User user);

}
