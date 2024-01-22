package ru.personal.task.socialmedia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.personal.task.socialmedia.entity.Role;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MyDatabaseUser {

    @NotBlank(message = "This field cannot be empty")
    @Size(min = 4, max = 32, message = "Username should contain from 2 to 32 signs")
    private String username;

    @NotBlank(message = "This field cannot be empty")
    @Size(min = 4, max = 32, message = "Email should contain from 4 to 32 signs")
    @Email
    private String email;

    @NotBlank(message = "This field cannot be empty")
    @Size(min = 4, max = 16, message = "Password should contain from 8 to 16 signs")
    private String password;

    private Role role;

}
