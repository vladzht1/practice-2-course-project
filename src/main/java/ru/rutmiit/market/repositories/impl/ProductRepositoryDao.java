package ru.rutmiit.market.repositories.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.rutmiit.market.domain.Product;
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
}
