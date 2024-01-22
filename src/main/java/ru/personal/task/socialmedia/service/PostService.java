package ru.personal.task.socialmedia.service;

import ru.personal.task.socialmedia.dto.CreateOrUpdatePost;
import ru.personal.task.socialmedia.dto.PostDTO;
import ru.personal.task.socialmedia.security.jwt.JwtAuthentication;

import java.util.List;

public interface PostService {

    PostDTO createPost(CreateOrUpdatePost createOrUpdatePost, JwtAuthentication authInfo);

    List<PostDTO> getAllPosts ();

    PostDTO updatePostById (long id, CreateOrUpdatePost createOrUpdatePost);

    void deletePostById (long id);

}
