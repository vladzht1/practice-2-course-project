package ru.rutmiit.market.exceptions;

public class MarketNotFoundException extends RuntimeException {
    public MarketNotFoundException() {
        super("Market was not found");
    }

    public MarketNotFoundException(int id) {
        super("Market with id " + id + " was not found");
    }
}
