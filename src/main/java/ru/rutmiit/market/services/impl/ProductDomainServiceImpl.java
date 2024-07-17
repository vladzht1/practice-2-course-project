package ru.rutmiit.market.services.impl;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.rutmiit.market.domain.OrderPosition;
import ru.rutmiit.market.domain.Product;
import ru.rutmiit.market.dtos.ProductDto;
import ru.rutmiit.market.dtos.api.AddProductDto;
import ru.rutmiit.market.dtos.api.UpdateProductDto;
import ru.rutmiit.market.dtos.internal.MarketReportUnitDto;
import ru.rutmiit.market.repositories.OrderRepository;
import ru.rutmiit.market.repositories.ProductRepository;
import ru.rutmiit.market.services.ProductService;

@Service
public class ProductDomainServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    private ModelMapper mapper;

    public ProductDomainServiceImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map((product) -> mapper.map(product, ProductDto.class)).toList();
    }

    @Override
    public List<MarketReportUnitDto> getBestSoldProductsByMarketId(int marketId) {
        Set<Product> soldProducts = new HashSet<>(productRepository.findBestSoldProductsByMarketId(marketId));
        List<MarketReportUnitDto> reportUnits = new ArrayList<>();

        for (var product : soldProducts) {
            List<OrderPosition> orderPosition = orderRepository.findOrderPositionsByProductId(product.getId());

            double income = 0;
            int amountSold = 0;

            for (var position : orderPosition) {
                income += position.getPrice() * position.getQuantity() * (1 - position.getDiscount());
                amountSold += position.getQuantity();
            }

            reportUnits.add(new MarketReportUnitDto(product.getTitle(), product.getDescription(), product.getProductCategory(), income, amountSold));
        }

        return reportUnits.stream().sorted((prev, next) -> (int)(next.getIncome() - prev.getIncome())).toList();
    }

    @Override
    public Optional<ProductDto> findById(Integer id) {
        Optional<Product> productOpt = productRepository.findById(id);

        if (productOpt.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(mapper.map(productOpt.get(), ProductDto.class));
    }

    @Override
    public ProductDto add(AddProductDto productDto) {
        return mapper.map(productRepository.save(mapper.map(productDto, Product.class)), ProductDto.class);
    }

    @Override
    public Optional<ProductDto> update(UpdateProductDto productDto) {
        Optional<Product> productOpt = productRepository.findById(productDto.getId());

        if (productOpt.isEmpty()) {
            return Optional.empty();
        }

        Product product = productOpt.get();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setProductCategory(productDto.getProductCategory());

        if (productRepository.update(product).isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(mapper.map(product, ProductDto.class));
    }
}
