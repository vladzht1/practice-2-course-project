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

import ru.rutmiit.market.dtos.MarketDto;
import ru.rutmiit.market.dtos.api.AddMarketDto;
import ru.rutmiit.market.dtos.api.UpdateMarketDto;
import ru.rutmiit.market.exceptions.MarketNotFoundException;
import ru.rutmiit.market.services.MarketService;

@RestController
@RequestMapping("/api/markets")
public class MarketRestController {
    @Autowired
    private MarketService marketService;

    @GetMapping()
    public Iterable<MarketDto> getAllMarkets() {
        return marketService.findAll();
    }

    @GetMapping("/{id}")
    public MarketDto getMarketById(@PathVariable() int id) throws MarketNotFoundException {
        Optional<MarketDto> marketOpt = marketService.findById(id);

        if (!marketOpt.isPresent()) {
            throw new MarketNotFoundException(id);
        }

        return marketOpt.get();
    }

    @PostMapping()
    public MarketDto create(@RequestBody AddMarketDto addMarketDto) {
        return marketService.add(addMarketDto);
    }

    @PatchMapping()
    public MarketDto update(@RequestBody UpdateMarketDto updateMarketDto) throws MarketNotFoundException {
        Optional<MarketDto> marketOpt = marketService.update(updateMarketDto);

        if (!marketOpt.isPresent()) {
            throw new MarketNotFoundException(updateMarketDto.getId());
        }

        return marketOpt.get();
    }
}
