package ru.rutmiit.market.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.rutmiit.market.domain.Market;
import ru.rutmiit.market.domain.MarketProduct;
import ru.rutmiit.market.domain.Product;
import ru.rutmiit.market.dtos.MarketProductDto;
import ru.rutmiit.market.dtos.api.AddMarketProductDto;
import ru.rutmiit.market.dtos.api.UpdateMarketProductDto;
import ru.rutmiit.market.exceptions.MarketNotFoundException;
import ru.rutmiit.market.exceptions.MarketProductNotFoundException;
import ru.rutmiit.market.exceptions.ProductNotFoundException;
import ru.rutmiit.market.repositories.MarketProductRepository;
import ru.rutmiit.market.repositories.MarketRepository;
import ru.rutmiit.market.repositories.ProductRepository;
import ru.rutmiit.market.services.MarketProductService;

@Service
public class MarketProductDomainServiceImpl implements MarketProductService {
    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MarketProductRepository marketProductRepository;

    private ModelMapper mapper;

    public MarketProductDomainServiceImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<MarketProductDto> findAll() {
        return marketProductRepository.findAll().stream().map(marketProduct -> mapper.map(marketProduct, MarketProductDto.class)).toList();
    }

    @Override
    public Optional<MarketProductDto> findById(int id) {
        Optional<MarketProduct> marketProductOpt = marketProductRepository.findById(id);

        if (marketProductOpt.isEmpty()) {
            return Optional.empty();
        }

        MarketProduct marketProduct = marketProductOpt.get();

        return Optional.of(mapper.map(marketProduct, MarketProductDto.class));
    }

    @Override
    public MarketProductDto add(AddMarketProductDto addMarketProduct) {
        Product product = productRepository.findById(addMarketProduct.getProductId()).orElseThrow(
            () -> new ProductNotFoundException(addMarketProduct.getProductId())
        );

        Market market = marketRepository.findById(addMarketProduct.getMarketId()).orElseThrow(
            () -> new MarketNotFoundException(addMarketProduct.getMarketId())
        );

        MarketProduct marketProduct = new MarketProduct(product, market, addMarketProduct.getPrice(), addMarketProduct.getQuantity());
        return mapper.map(marketProductRepository.save(marketProduct), MarketProductDto.class);
    }

    @Override
    public Optional<MarketProductDto> update(UpdateMarketProductDto updateMarketProduct) {
        Optional<MarketProduct> marketProductOpt = marketProductRepository.findById(updateMarketProduct.getId());

        if (marketProductOpt.isEmpty()) {
            throw new MarketProductNotFoundException(updateMarketProduct.getId());
        }

        MarketProduct marketProduct = marketProductOpt.get();

        if (updateMarketProduct.getMarketId() != 0) {
            Optional<Market> market = marketRepository.findById(updateMarketProduct.getMarketId());

            if (market.isEmpty()) {
                throw new MarketNotFoundException(updateMarketProduct.getMarketId());
            }

            marketProduct.setMarket(market.get());
        }

        if (updateMarketProduct.getProductId() != 0) {
            Optional<Product> product = productRepository.findById(updateMarketProduct.getProductId());

            if (product.isEmpty()) {
                throw new ProductNotFoundException(updateMarketProduct.getProductId());
            }

            marketProduct.setProduct(product.get());
        }

        marketProduct.setQuantity(updateMarketProduct.getQuantity());
        marketProduct.setPrice(updateMarketProduct.getPrice());

        if (marketProductRepository.update(marketProduct).isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(mapper.map(marketProduct, MarketProductDto.class));
    }

    @Override
    public void remove(int id) {
        if (marketProductRepository.findById(id).isEmpty()) {
            throw new MarketProductNotFoundException(id);
        }

        marketProductRepository.deleteById(id);
    }
}
