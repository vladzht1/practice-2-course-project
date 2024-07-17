package ru.rutmiit.market.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.rutmiit.market.domain.Order;
import ru.rutmiit.market.domain.OrderPosition;
import ru.rutmiit.market.dtos.internal.MarketSaleRecommendationDto;
import ru.rutmiit.market.repositories.OrderRepository;
import ru.rutmiit.market.services.DiscountRecommendationsService;

@Service
public class DiscountRecommendationsDomainServiceImpl implements DiscountRecommendationsService {
    private static final int DEFAULT_DISCOUNT_RECOMMENDATIONS_AMOUNT = 5;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<MarketSaleRecommendationDto> getRecommendationsForDiscountsAndSalesByMarketId(int marketId) {
        List<Order> marketOrders = orderRepository.findAllOrdersByMarketId(marketId);

        List<OrderPosition> marketOrderPositions = marketOrders
            .stream()
            .map(order -> order.getOrderPositions())
            .reduce(new ArrayList<>(), (List<OrderPosition> acc, List<OrderPosition> value) -> {
                value.forEach(position -> acc.add(position));
                return acc;
            })
            .stream()
            .sorted((prev, next) -> (int)((prev.getPrice() * prev.getQuantity()) - (next.getPrice() * next.getQuantity())))
            .toList();

        marketOrderPositions = marketOrderPositions.subList(0, Math.min(DEFAULT_DISCOUNT_RECOMMENDATIONS_AMOUNT, marketOrderPositions.size()));

        List<MarketSaleRecommendationDto> result = new ArrayList<>();

        for (int i = 0; i < marketOrderPositions.size(); i++) {
            OrderPosition position = marketOrderPositions.get(i);

            result.add(new MarketSaleRecommendationDto(
                position.getProduct().getTitle(),
                position.getProduct().getDescription(),
                position.getProduct().getProductCategory(),
                position.getPrice(),
                Double.parseDouble(String.format("%.2f", Math.random() / (i + 1)))
            ));
        }

        return result;
    }
}
