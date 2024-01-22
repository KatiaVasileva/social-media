package ru.personal.task.socialmedia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.personal.task.socialmedia.entity.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class UserDTO {

    private long id;

    private String username;

    @NotBlank(message = "This field cannot be empty")
    @Size(min = 4, max = 32, message = "Login should contain from 4 to 32 signs")
    @Email
    private String email;

    private long imageId;

    private Role role;
}
