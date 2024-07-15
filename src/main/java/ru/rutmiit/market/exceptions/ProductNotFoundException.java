package ru.rutmiit.market.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Product was not found");
    }

    public ProductNotFoundException(int id) {
        super("Product with id " + id + " was not found");
    }
}
