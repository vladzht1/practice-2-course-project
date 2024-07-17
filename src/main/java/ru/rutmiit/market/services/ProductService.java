package ru.rutmiit.market.services;

import java.util.List;
import java.util.Optional;

import ru.rutmiit.market.dtos.ProductDto;
import ru.rutmiit.market.dtos.api.AddProductDto;
import ru.rutmiit.market.dtos.api.UpdateProductDto;
import ru.rutmiit.market.dtos.internal.MarketReportUnitDto;

public interface ProductService {
    List<ProductDto> findAll();
    List<MarketReportUnitDto> getBestSoldProductsByMarketId(int marketId);
    Optional<ProductDto> findById(Integer id);
    ProductDto add(AddProductDto productDto);
    Optional<ProductDto> update(UpdateProductDto productDto);
}
