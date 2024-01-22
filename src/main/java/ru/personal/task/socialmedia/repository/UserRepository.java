package ru.personal.task.socialmedia.repository;

import org.springframework.data.repository.CrudRepository;
import ru.personal.task.socialmedia.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
