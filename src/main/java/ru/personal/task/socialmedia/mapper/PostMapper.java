package ru.personal.task.socialmedia.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import ru.personal.task.socialmedia.dto.CreateOrUpdatePost;
import ru.personal.task.socialmedia.dto.PostDTO;
import ru.personal.task.socialmedia.entity.Post;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class PostMapper {

    public abstract void createOrUpdatePostToPost (CreateOrUpdatePost createOrUpdatePost,
                                                   @MappingTarget Post post);

    public abstract PostDTO postToPostDTO (Post post);

}
