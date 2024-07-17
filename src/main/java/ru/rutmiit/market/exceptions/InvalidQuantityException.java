package ru.rutmiit.market.exceptions;

public class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException() {
        super("Invalid quantity");
    }

    public InvalidQuantityException(String message) {
        super(message);
    }
}
