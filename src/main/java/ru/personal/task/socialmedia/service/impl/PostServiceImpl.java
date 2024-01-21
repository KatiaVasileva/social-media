package ru.personal.task.socialmedia.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.personal.task.socialmedia.dto.CreateOrUpdatePost;
import ru.personal.task.socialmedia.dto.PostDTO;
import ru.personal.task.socialmedia.entity.Post;
import ru.personal.task.socialmedia.exception.PostNotFoundException;
import ru.personal.task.socialmedia.mapper.PostMapper;
import ru.personal.task.socialmedia.repository.PostRepository;
import ru.personal.task.socialmedia.service.PostService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public PostDTO createPost(CreateOrUpdatePost createOrUpdatePost) {
        Post post = new Post();
        postMapper.createOrUpdatePostToPost(createOrUpdatePost, post);
        postRepository.save(post);
        return postMapper.postToPostDTO(postRepository.findById(post.getId()).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        postRepository.findAll().forEach(posts::add);
        return posts.stream().map(postMapper::postToPostDTO).toList();
    }

    @Override
    public PostDTO updatePostById (long id, CreateOrUpdatePost createOrUpdatePost) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        postMapper.createOrUpdatePostToPost(createOrUpdatePost, post);
        Post updatedPost = postRepository.save(post);
        return postMapper.postToPostDTO(updatedPost);
    }

    @Override
    public void deletePostById (long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        postRepository.delete(post);
    }
}
