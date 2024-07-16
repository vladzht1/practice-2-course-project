package ru.rutmiit.market.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.rutmiit.market.domain.Market;
import ru.rutmiit.market.dtos.MarketDto;
import ru.rutmiit.market.dtos.api.AddMarketDto;
import ru.rutmiit.market.dtos.api.UpdateMarketDto;
import ru.rutmiit.market.exceptions.MarketAlreadyExistsException;
import ru.rutmiit.market.exceptions.MarketNotFoundException;
import ru.rutmiit.market.repositories.MarketRepository;
import ru.rutmiit.market.services.MarketService;

@Service
public class MarketServiceImpl implements MarketService {
    @Autowired
    private MarketRepository marketRepository;

    private ModelMapper mapper;

    public MarketServiceImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<MarketDto> findAll() {
        return marketRepository.findAll().stream().map((market) -> mapper.map(market, MarketDto.class)).toList();
    }

    @Override
    public Optional<MarketDto> findById(Integer id) {
        Optional<Market> market = marketRepository.findById(id);

        if (!market.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(mapper.map(market.get(), MarketDto.class));
    }

    @Override
    public MarketDto add(AddMarketDto marketDto) {
        Optional<Market> existingMarketWithName = marketRepository.findMarketByName(marketDto.getName());

        if (existingMarketWithName.isPresent()) {
            throw new MarketAlreadyExistsException(marketDto.getName());
        }

        Market market = mapper.map(marketDto, Market.class);
        return mapper.map(marketRepository.save(market), MarketDto.class);
    }

    @Override
    public Optional<MarketDto> update(UpdateMarketDto marketDto) {
        Optional<Market> marketOpt = marketRepository.findById(marketDto.getId());

        if (marketOpt.isEmpty()) {
            throw new MarketNotFoundException(marketDto.getId());
        }

        System.out.println(marketDto.getName() + " " + marketDto.getDescription());

        Market market = marketOpt.get();
        market.setName(marketDto.getName());
        market.setDescription(marketDto.getDescription());

        if (marketRepository.update(market).isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(mapper.map(market, MarketDto.class));
    }
}
