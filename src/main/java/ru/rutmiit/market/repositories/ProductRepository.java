package ru.rutmiit.market.repositories;

import java.util.List;
import java.util.Optional;

import ru.rutmiit.market.domain.Product;

public interface ProductRepository {
    List<Product> findAll();
    Optional<Product> findById(int id);
    Product save(Product product);
    Optional<Product> update(Product user);
}
