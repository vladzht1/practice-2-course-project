package ru.rutmiit.market.services.prices;

import java.util.List;

import ru.rutmiit.market.domain.OrderPosition;

public class PriceCounterImpl implements PriceCounter {
    public double calculatePrice(List<OrderPosition> orderPositions) {
        double result = 0;

        for (var orderPosition : orderPositions) {
            result += calculatePrice(orderPosition);
        }

        return result;
    }

    public double calculatePrice(OrderPosition orderPosition) {
        return orderPosition.getQuantity() * orderPosition.getPrice() * (1 - orderPosition.getDiscount());
    }
}
