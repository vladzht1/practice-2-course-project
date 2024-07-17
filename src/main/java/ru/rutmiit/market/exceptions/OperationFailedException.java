package ru.rutmiit.market.exceptions;

public class OperationFailedException extends RuntimeException{
    public OperationFailedException(String message) {
        super(message);
    }
}
