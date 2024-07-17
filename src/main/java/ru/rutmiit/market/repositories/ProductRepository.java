package ru.rutmiit.market.repositories;

import java.util.List;
import java.util.Optional;

import ru.rutmiit.market.domain.Product;
import ru.rutmiit.market.domain.ProductCategory;

public interface ProductRepository {
    List<Product> findAll();
    List<Product> findAmountByCategories(List<ProductCategory> categories);
    List<Product> findBestSoldProductsByMarketId(int marketId);
    Optional<Product> findById(int id);
    Product save(Product product);
    Optional<Product> update(Product user);
}
