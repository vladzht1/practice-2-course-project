package ru.rutmiit.market.services;

import java.util.List;
import java.util.Optional;

import ru.rutmiit.market.dtos.MarketProductDto;
import ru.rutmiit.market.dtos.api.AddMarketProductDto;
import ru.rutmiit.market.dtos.api.UpdateMarketProductDto;

public interface MarketProductService {
    List<MarketProductDto> findAll();
    Optional<MarketProductDto> findById(int id);
    MarketProductDto add(AddMarketProductDto marketProduct);
    Optional<MarketProductDto> update(UpdateMarketProductDto marketProduct);
    void remove(int id);
}
