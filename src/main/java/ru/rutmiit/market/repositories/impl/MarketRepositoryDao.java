package ru.rutmiit.market.repositories.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.rutmiit.market.domain.Market;
import ru.rutmiit.market.repositories.MarketRepository;

@Repository
public class MarketRepositoryDao implements MarketRepository {
    @Autowired
    private BaseMarketRepository baseMarketRepository;

    @Override
    public List<Market> findAll() {
        return baseMarketRepository.findAll();
    }

    @Override
    public Optional<Market> findById(int id) {
        return baseMarketRepository.findById(id);
    }

    @Override
    public Optional<Market> findMarketByName(String name) {
        return Optional.ofNullable(baseMarketRepository.findMarketByName(name));
    }

    @Override
    public Market save(Market market) {
        return baseMarketRepository.save(market);
    }

    @Override
    public Optional<Market> update(Market market) {
        return Optional.of(baseMarketRepository.save(market));
    }
}

@Repository
interface BaseMarketRepository extends JpaRepository<Market, Integer> {
    @Query(value = "select m from Market m where m.name = :name")
    Market findMarketByName(@Param("name") String name);
}
