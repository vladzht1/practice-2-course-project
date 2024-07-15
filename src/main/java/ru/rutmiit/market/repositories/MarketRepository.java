package ru.rutmiit.market.repositories;

import java.util.List;
import java.util.Optional;

import ru.rutmiit.market.domain.Market;

public interface MarketRepository {
    List<Market> findAll();
    Optional<Market> findById(int id);
    Optional<Market> findMarketByName(String name);
    Market save(Market market);
    Optional<Market> update(Market market);
}
