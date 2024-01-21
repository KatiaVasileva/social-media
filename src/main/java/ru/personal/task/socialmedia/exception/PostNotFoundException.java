package ru.personal.task.socialmedia.exception;

public class PostNotFoundException extends RuntimeException{

    public static final String DEFAULT_MESSAGE = "Post is not found";

    public PostNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

}
