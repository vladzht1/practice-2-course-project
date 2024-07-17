package ru.rutmiit.market.services;

import java.util.List;
import java.util.Optional;

import ru.rutmiit.market.dtos.OrderDto;
import ru.rutmiit.market.dtos.api.AddOrderDto;
import ru.rutmiit.market.dtos.api.UpdateOrderDto;

public interface OrderService {
    List<OrderDto> findAll();
    Optional<OrderDto> findById(int id);
    List<OrderDto> add(AddOrderDto orderDto);
    Optional<OrderDto> update(UpdateOrderDto orderDto);
}
