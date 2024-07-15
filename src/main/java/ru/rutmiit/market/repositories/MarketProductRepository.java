package ru.rutmiit.market.repositories;

import java.util.List;
import java.util.Optional;

import ru.rutmiit.market.domain.MarketProduct;

public interface MarketProductRepository {
    List<MarketProduct> findAll();
    Optional<MarketProduct> findById(int id);
    MarketProduct save(MarketProduct market);
    Optional<MarketProduct> update(MarketProduct market);
    void deleteById(int id);
}
