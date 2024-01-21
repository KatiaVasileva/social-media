package ru.personal.task.socialmedia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CreateOrUpdatePost {

    @NotBlank(message = "This field cannot be empty")
    @Size(min = 4, max = 255, message = "The title must contain minimum 4 and maximum 255 symbols")
    private String title;

    @NotBlank(message = "This field cannot be empty")
    @Size(min = 4, max = 255, message = "The title must contain minimum 4 and maximum 255 symbols")
    private String text;

    private long imageId;

}
