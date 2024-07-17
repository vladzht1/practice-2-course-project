package ru.rutmiit.market.mappers;

import org.modelmapper.ModelMapper;

import ru.rutmiit.market.domain.MarketProduct;
import ru.rutmiit.market.domain.Order;
import ru.rutmiit.market.dtos.MarketProductDto;
import ru.rutmiit.market.dtos.OrderDto;

public class ModelMapperFactory {
    private static ModelMapper modelMapper;

    public ModelMapper getMapper() {
        if (modelMapper == null) {
            modelMapper = initializeModelMapper(new ModelMapper());
        }

        return modelMapper;
    }

    private ModelMapper initializeModelMapper(ModelMapper modelMapper) {
        modelMapper
            .typeMap(MarketProduct.class, MarketProductDto.class)
            .addMappings(mapper -> {
                mapper.map(marketProduct -> marketProduct.getMarket().getId(), MarketProductDto::setMarketId);
                mapper.map(marketProduct -> marketProduct.getProduct().getId(), MarketProductDto::setProductId);
                mapper.map(marketProduct -> marketProduct.getProduct().getTitle(), MarketProductDto::setProductTitle);
                mapper.map(marketProduct -> marketProduct.getProduct().getDescription(), MarketProductDto::setProductDescription);
                mapper.map(marketProduct -> marketProduct.getProduct().getProductCategory(), MarketProductDto::setProductCategory);
                mapper.map(marketProduct -> marketProduct.getPrice(), MarketProductDto::setPrice);
                mapper.map(marketProduct -> marketProduct.getQuantity(), MarketProductDto::setQuantity);
            }
        );

        // NOTE: Uncomment this code to see it does not work :/

        // TypeMap<OrderPosition, OrderPositionDto> orderPositionToDto = modelMapper.typeMap(OrderPosition.class, OrderPositionDto.class);
        // orderPositionToDto.addMappings(mapper -> {
        //         mapper.map(orderPosition -> orderPosition.getProduct().getTitle(), OrderPositionDto::setTitle);
        //         mapper.map(orderPosition -> orderPosition.getProduct().getDescription(), OrderPositionDto::setDescription);
        //         mapper.map(orderPosition -> orderPosition.getProduct().getProductCategory(), OrderPositionDto::setCategory);
        //         mapper.map(orderPosition -> orderPosition.getPrice(), OrderPositionDto::setPrice);
        //         mapper.map(orderPosition -> orderPosition.getDiscount(), OrderPositionDto::setDiscount);
        //         mapper.map(orderPosition -> orderPosition.getQuantity(), OrderPositionDto::setQuantity);
        //         mapper.map(orderPosition -> priceCounter.calculatePrice(orderPosition), OrderPositionDto::setTotalPrice);
        //     }
        // );

        // Converter<List<OrderPosition>, List<OrderPositionDto>> orderPositionConverter = ctx -> ctx.getSource()
        //     .stream()
        //     .map(position -> orderPositionToDto.map(position))
        //     .collect(Collectors.toList());

        modelMapper
            .typeMap(Order.class, OrderDto.class)
            .addMappings(mapper -> {
                mapper.map(order -> order.getId(), OrderDto::setId);
                mapper.map(order -> order.getUser().getId(), OrderDto::setUserId);
                mapper.map(order -> order.getMarket().getId(), OrderDto::setMarketId);
                mapper.map(order -> order.getCreationDate(), OrderDto::setCreatedAt);
                mapper.map(order -> order.getLastUpdatedDate(), OrderDto::setUpdatedAt);
                mapper.map(order -> order.getStatus(), OrderDto::setStatus);
                // mapper.using(orderPositionConverter).map(Order::getOrderPositions, OrderDto::setProducts);
            }
        );

        return modelMapper;
    }
}
