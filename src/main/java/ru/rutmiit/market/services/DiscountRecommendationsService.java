package ru.rutmiit.market.services;

import java.util.List;

import ru.rutmiit.market.dtos.internal.MarketSaleRecommendationDto;

public interface DiscountRecommendationsService {
    List<MarketSaleRecommendationDto> getRecommendationsForDiscountsAndSalesByMarketId(int marketId);
}
