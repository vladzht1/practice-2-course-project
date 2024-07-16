package ru.rutmiit.market.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import ru.rutmiit.market.domain.MarketProduct;
import ru.rutmiit.market.dtos.MarketProductDto;

public class ModelMapperFactory {
    private static ModelMapper modelMapper;

    public ModelMapper getMapper() {
        if (modelMapper == null) {
            modelMapper = initializeModelMapper(new ModelMapper());
        }

        return modelMapper;
    }

    private ModelMapper initializeModelMapper(ModelMapper modelMapper) {
        TypeMap<MarketProduct, MarketProductDto> marketProductToDto = modelMapper.typeMap(MarketProduct.class, MarketProductDto.class);

        marketProductToDto.addMappings(mapper -> {
            mapper.map(marketProduct -> marketProduct.getMarket().getId(), MarketProductDto::setMarketId);
            mapper.map(marketProduct -> marketProduct.getProduct().getId(), MarketProductDto::setProductId);
            mapper.map(marketProduct -> marketProduct.getProduct().getTitle(), MarketProductDto::setProductTitle);
            mapper.map(marketProduct -> marketProduct.getProduct().getDescription(), MarketProductDto::setProductDescription);
            mapper.map(marketProduct -> marketProduct.getProduct().getProductCategory(), MarketProductDto::setProductCategory);
            mapper.map(marketProduct -> marketProduct.getPrice(), MarketProductDto::setPrice);
            mapper.map(marketProduct -> marketProduct.getQuantity(), MarketProductDto::setQuantity);
        });

        return modelMapper;
    }
}
