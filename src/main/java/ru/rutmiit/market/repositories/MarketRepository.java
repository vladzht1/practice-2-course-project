package ru.rutmiit.market.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.rutmiit.market.domain.Market;

@Repository
public interface MarketRepository extends JpaRepository<Market, Integer> {
}
