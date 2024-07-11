package ru.rutmiit.market.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.rutmiit.market.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
