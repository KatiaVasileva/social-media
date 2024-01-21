package ru.personal.task.socialmedia.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String text;

    @Column(name = "image_id")
    private long imageId;

    @Column(name = "post_time")
    private LocalDateTime postTime = LocalDateTime.now();

//    @Column(name = "user_id")
//    private long userId;

}
