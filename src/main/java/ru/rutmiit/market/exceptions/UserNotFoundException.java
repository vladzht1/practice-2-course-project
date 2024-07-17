package ru.rutmiit.market.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User was not found");
    }

    public UserNotFoundException(int id) {
        super("User with id " + id + " was not found");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
