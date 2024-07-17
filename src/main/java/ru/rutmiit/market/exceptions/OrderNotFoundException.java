package ru.rutmiit.market.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super("Order was not found");
    }

    public OrderNotFoundException(int id) {
        super("Order with id " + id + " was not found");
    }
}
