package ru.personal.task.socialmedia.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.personal.task.socialmedia.dto.CreateOrUpdatePost;
import ru.personal.task.socialmedia.dto.PostDTO;
import ru.personal.task.socialmedia.security.jwt.JwtAuthentication;
import ru.personal.task.socialmedia.service.AuthService;
import ru.personal.task.socialmedia.service.PostService;

import java.util.List;

@RestController
@Validated
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final AuthService authService;

    @PostMapping("/new")
    public PostDTO createPost (@Valid @RequestBody CreateOrUpdatePost createOrUpdatePost) {
        final JwtAuthentication authInfo = authService.getAuthInfo();
        return postService.createPost(createOrUpdatePost, authInfo);
    }

    @GetMapping("/all")
    public List<PostDTO> getAllPosts () {
        return postService.getAllPosts();
    }

    @PatchMapping("/{id}")
    public PostDTO updatePostById (@PathVariable long id,
                                   @Valid @RequestBody CreateOrUpdatePost createOrUpdatePost) {
        return postService.updatePostById(id, createOrUpdatePost);
    }

    @DeleteMapping("/{id}")
    public void deletePostById (@PathVariable long id) {
        postService.deletePostById(id);
    }
}
