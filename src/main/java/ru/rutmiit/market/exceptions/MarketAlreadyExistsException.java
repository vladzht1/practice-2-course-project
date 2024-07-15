package ru.rutmiit.market.exceptions;

public class MarketAlreadyExistsException extends RuntimeException {
    public MarketAlreadyExistsException() {
        super("Market already exists");
    }

    public MarketAlreadyExistsException(String name) {
        super("Market with name " + name + " already exists");
    }
}
