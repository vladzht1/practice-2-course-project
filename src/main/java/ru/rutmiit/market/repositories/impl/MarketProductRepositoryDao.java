package ru.rutmiit.market.repositories.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.rutmiit.market.domain.MarketProduct;
import ru.rutmiit.market.repositories.MarketProductRepository;

@Repository
public class MarketProductRepositoryDao implements MarketProductRepository {
    @Autowired
    private BaseMarketProductRepository baseMarketProductRepository;

    @Override
    public List<MarketProduct> findAll() {
        return baseMarketProductRepository.findAll();
    }

    @Override
    public Optional<MarketProduct> findById(int id) {
        return baseMarketProductRepository.findById(id);
    }

    @Override
    public MarketProduct save(MarketProduct marketProduct) {
        return baseMarketProductRepository.save(marketProduct);
    }

    @Override
    public Optional<MarketProduct> update(MarketProduct marketProduct) {
        return Optional.ofNullable(baseMarketProductRepository.save(marketProduct));
    }

    @Override
    public void deleteById(int id) {
        baseMarketProductRepository.deleteById(id);
    }
}

@Repository
interface BaseMarketProductRepository extends JpaRepository<MarketProduct, Integer> {
}
