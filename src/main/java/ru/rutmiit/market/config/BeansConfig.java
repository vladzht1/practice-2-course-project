package ru.rutmiit.market.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.rutmiit.market.mappers.ModelMapperFactory;
import ru.rutmiit.market.mappers.OrderMapper;
import ru.rutmiit.market.services.prices.PriceCounter;
import ru.rutmiit.market.services.prices.PriceCounterImpl;

@Configuration
public class BeansConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapperFactory().getMapper();
    }

    @Bean
    public PriceCounter priceCounter() {
        return new PriceCounterImpl();
    }

    @Bean
    public OrderMapper orderMapper() {
        return new OrderMapper(modelMapper(), priceCounter());
    }
}
