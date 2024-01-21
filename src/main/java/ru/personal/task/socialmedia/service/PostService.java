package ru.personal.task.socialmedia.service;

import ru.personal.task.socialmedia.dto.CreateOrUpdatePost;
import ru.personal.task.socialmedia.dto.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO createPost(CreateOrUpdatePost createOrUpdatePost);

    List<PostDTO> getAllPosts ();

    PostDTO updatePostById (long id, CreateOrUpdatePost createOrUpdatePost);

    void deletePostById (long id);

}
