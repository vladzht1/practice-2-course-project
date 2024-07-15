package ru.rutmiit.market.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.rutmiit.market.dtos.ProductDto;
import ru.rutmiit.market.dtos.api.AddProductDto;
import ru.rutmiit.market.dtos.api.UpdateProductDto;
import ru.rutmiit.market.exceptions.ProductNotFoundException;
import ru.rutmiit.market.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public Iterable<ProductDto> getAllProduct() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable() int id) throws ProductNotFoundException {
        Optional<ProductDto> productOpt = productService.findById(id);

        if (!productOpt.isPresent()) {
            throw new ProductNotFoundException(id);
        }

        return productOpt.get();
    }

    @PostMapping()
    public ProductDto create(@RequestBody AddProductDto addProductDto) {
        return productService.add(addProductDto);
    }

    @PatchMapping()
    public ProductDto update(@RequestBody UpdateProductDto updateProductDto) throws ProductNotFoundException {
        Optional<ProductDto> productOpt = productService.update(updateProductDto);

        if (!productOpt.isPresent()) {
            throw new ProductNotFoundException(updateProductDto.getId());
        }

        return productOpt.get();
    }
}
