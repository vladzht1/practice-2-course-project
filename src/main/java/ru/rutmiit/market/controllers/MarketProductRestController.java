package ru.rutmiit.market.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.rutmiit.market.dtos.MarketProductDto;
import ru.rutmiit.market.dtos.api.AddMarketProductDto;
import ru.rutmiit.market.dtos.api.UpdateMarketProductDto;
import ru.rutmiit.market.exceptions.MarketProductNotFoundException;
import ru.rutmiit.market.services.MarketProductService;

@RestController
@RequestMapping("/api/market_products")
public class MarketProductRestController {
    @Autowired
    private MarketProductService marketProductService;

    @GetMapping()
    public Iterable<MarketProductDto> getAllMarketProducts() {
        return marketProductService.findAll();
    }

    @GetMapping("/{id}")
    public MarketProductDto getMarketProductById(@PathVariable() int id) throws MarketProductNotFoundException {
        Optional<MarketProductDto> marketProductOpt = marketProductService.findById(id);

        if (marketProductOpt.isEmpty()) {
            throw new MarketProductNotFoundException(id);
        }

        return marketProductOpt.get();
    }

    @PostMapping()
    public MarketProductDto create(@RequestBody AddMarketProductDto addMarketProductDto) {
        return marketProductService.add(addMarketProductDto);
    }

    @PatchMapping()
    public MarketProductDto update(@RequestBody UpdateMarketProductDto updateMarketProductDto) throws MarketProductNotFoundException {
        Optional<MarketProductDto> marketProductOpt = marketProductService.update(updateMarketProductDto);

        if (marketProductOpt.isEmpty()) {
            throw new MarketProductNotFoundException(updateMarketProductDto.getId());
        }

        return marketProductOpt.get();
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable int id) {
        marketProductService.remove(id);
    }
}
