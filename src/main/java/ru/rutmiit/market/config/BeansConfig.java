package ru.rutmiit.market.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.rutmiit.market.mappers.ModelMapperFactory;

@Configuration
public class BeansConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapperFactory().getMapper();
    }
}
