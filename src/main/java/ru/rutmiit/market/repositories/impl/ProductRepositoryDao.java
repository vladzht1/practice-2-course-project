package ru.rutmiit.market.repositories.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.rutmiit.market.domain.Product;
import ru.rutmiit.market.domain.ProductCategory;
import ru.rutmiit.market.repositories.ProductRepository;

@Repository
public class ProductRepositoryDao implements ProductRepository {
    @Autowired
    private BaseProductRepository baseProductRepository;

    @Override
    public List<Product> findAll() {
        return baseProductRepository.findAll();
    }

    @Override
    public List<Product> findAmountByCategories(List<ProductCategory> categories) {
        return baseProductRepository.findByCategoriesWithLimit(categories);
    }

    @Override
    public List<Product> findBestSoldProductsByMarketId(int marketId) {
        return baseProductRepository.findMostSoldProductsByMarketIdOrderByAmount(marketId);
    }

    @Override
    public Optional<Product> findById(int id) {
        return baseProductRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return baseProductRepository.save(product);
    }

    @Override
    public Optional<Product> update(Product product) {
        return Optional.of(baseProductRepository.save(product));
    }
}

@Repository
interface BaseProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select product from Product product where product.productCategory in :categories")
    List<Product> findByCategoriesWithLimit(
        @Param("categories") List<ProductCategory> categories
    );

    @Query(value =
        "select product, sum(order_position.quantity) quantity from Product product "
        + " join OrderPosition order_position on order_position.product = product "
        + " join order_position.order order join order.market market where market.id = :market_id "
        + " group by product.id, product.productCategory "
        + " order by quantity desc"
    )
    List<Product> findMostSoldProductsByMarketIdOrderByAmount(@Param("market_id") int marketId);
}
