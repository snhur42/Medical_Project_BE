package com.bariatr.project.exception.model;

public class UserNotFoundException extends ModelException {
    public UserNotFoundException(String message) {
        super(message);
    }
}