package ru.rutmiit.market.services;

import java.util.List;
import java.util.Optional;

import ru.rutmiit.market.dtos.MarketDto;
import ru.rutmiit.market.dtos.api.AddMarketDto;
import ru.rutmiit.market.dtos.api.UpdateMarketDto;

public interface MarketService {
    List<MarketDto> findAll();
    Optional<MarketDto> findById(Integer id);
    MarketDto add(AddMarketDto productDto);
    Optional<MarketDto> update(UpdateMarketDto productDto);
}
