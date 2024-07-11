package ru.rutmiit.market.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.rutmiit.market.domain.MarketProduct;

@Repository
public interface MarketProductRepository extends JpaRepository<MarketProduct, Integer> {
}
