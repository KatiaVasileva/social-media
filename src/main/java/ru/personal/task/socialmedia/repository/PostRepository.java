package ru.personal.task.socialmedia.repository;

import org.springframework.data.repository.CrudRepository;
import ru.personal.task.socialmedia.entity.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
}
