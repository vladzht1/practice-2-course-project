package ru.rutmiit.market.services.prices;

import java.util.List;

import ru.rutmiit.market.domain.OrderPosition;

public interface PriceCounter {
    public double calculatePrice(List<OrderPosition> orderPositions);
    public double calculatePrice(OrderPosition orderPosition);
}
