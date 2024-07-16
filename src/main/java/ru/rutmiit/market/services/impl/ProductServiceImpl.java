package ru.rutmiit.market.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.rutmiit.market.domain.Product;
import ru.rutmiit.market.dtos.ProductDto;
import ru.rutmiit.market.dtos.api.AddProductDto;
import ru.rutmiit.market.dtos.api.UpdateProductDto;
import ru.rutmiit.market.repositories.ProductRepository;
import ru.rutmiit.market.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    private ModelMapper mapper;

    public ProductServiceImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map((product) -> mapper.map(product, ProductDto.class)).toList();
    }

    @Override
    public Optional<ProductDto> findById(Integer id) {
        Optional<Product> productOpt = productRepository.findById(id);

        if (!productOpt.isPresent()) {
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

        if (!productOpt.isPresent()) {
            return Optional.empty();
        }

        Product product = productOpt.get();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setProductCategory(productDto.getProductCategory());

        if (!productRepository.update(product).isPresent()) {
            return Optional.empty();
        }

        return Optional.of(mapper.map(product, ProductDto.class));
    }
}
