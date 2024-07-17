package ru.rutmiit.market.services;

import java.util.List;

import ru.rutmiit.market.dtos.MarketProductDto;

public interface UserRecommendationsService {
    List<MarketProductDto> findRecommendedProductsForUser(int userId);
}
