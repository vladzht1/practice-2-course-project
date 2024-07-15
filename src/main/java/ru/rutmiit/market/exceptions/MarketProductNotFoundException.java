package ru.rutmiit.market.exceptions;

public class MarketProductNotFoundException extends RuntimeException {
    public MarketProductNotFoundException() {
        super("Market product was not found");
    }

    public MarketProductNotFoundException(int id) {
        super("Market product with id " + id + " was not found");
    }
}
