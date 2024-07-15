package ru.rutmiit.market.services;

import java.util.List;
import java.util.Optional;

import ru.rutmiit.market.dtos.ProductDto;
import ru.rutmiit.market.dtos.api.AddProductDto;
import ru.rutmiit.market.dtos.api.UpdateProductDto;

public interface ProductService {
    List<ProductDto> findAll();
    Optional<ProductDto> findById(Integer id);
    ProductDto add(AddProductDto productDto);
    Optional<ProductDto> update(UpdateProductDto productDto);
}
