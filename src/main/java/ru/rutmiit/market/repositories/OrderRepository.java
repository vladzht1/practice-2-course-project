package ru.rutmiit.market.repositories;

import java.util.List;
import java.util.Optional;

import ru.rutmiit.market.domain.Order;
import ru.rutmiit.market.domain.OrderPosition;

public interface OrderRepository {
    List<Order> findAll();
    List<Order> findAllOrdersByUserId(int userId);
    List<Order> findAllOrdersByMarketId(int marketId);
    List<OrderPosition> findOrderPositionsByProductId(int productId);
    Optional<Order> findById(int id);
    Order save(Order order);
    OrderPosition saveOrderPosition(OrderPosition orderPosition);
    Optional<Order> update(Order order);
    Optional<OrderPosition> updateOrderPosition(OrderPosition orderPosition);
}
