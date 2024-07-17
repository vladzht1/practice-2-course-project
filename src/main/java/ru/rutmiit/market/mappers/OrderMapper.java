package ru.rutmiit.market.mappers;

import java.util.List;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;

import ru.rutmiit.market.domain.Order;
import ru.rutmiit.market.domain.OrderPosition;
import ru.rutmiit.market.dtos.OrderDto;
import ru.rutmiit.market.dtos.OrderPositionDto;
import ru.rutmiit.market.services.prices.PriceCounter;

public class OrderMapper {
    private ModelMapper modelMapper;
    private PriceCounter priceCounter;

    public OrderMapper(ModelMapper modelMapper, PriceCounter priceCounter) {
        this.modelMapper = modelMapper;
        this.priceCounter = priceCounter;
    }

    public List<OrderDto> mapOrdersToDto(List<Order> orders) {
        List<OrderDto> result = new ArrayList<>();

        for (Order order : orders) {
            result.add(mapOrderToDto(order));
        }

        return result;
    }

    public OrderDto mapOrderToDto(Order order) {
        OrderDto orderDto = modelMapper.map(order, OrderDto.class);
        orderDto.setProducts(mapOrderPositionsToDto(order.getOrderPositions()));

        return orderDto;
    }

    public List<OrderPositionDto> mapOrderPositionsToDto(List<OrderPosition> orderPositions) {
        List<OrderPositionDto> positionDtos = new ArrayList<>();

        for (OrderPosition position : orderPositions) {
            positionDtos.add(mapOrderPositionToDto(position));
        }

        return positionDtos;
    }

    public OrderPositionDto mapOrderPositionToDto(OrderPosition position) {
        return new OrderPositionDto(
            position.getProduct().getTitle(),
            position.getProduct().getDescription(),
            position.getProduct().getProductCategory(),
            position.getPrice(),
            position.getDiscount(),
            position.getQuantity(),
            priceCounter.calculatePrice(position)
        );
    }
}
