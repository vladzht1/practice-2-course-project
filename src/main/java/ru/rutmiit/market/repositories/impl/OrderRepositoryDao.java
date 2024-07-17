package ru.rutmiit.market.repositories.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.rutmiit.market.domain.Order;
import ru.rutmiit.market.domain.OrderPosition;
import ru.rutmiit.market.repositories.OrderRepository;

@Repository
public class OrderRepositoryDao implements OrderRepository {
    @Autowired
    private BaseOrderRepository baseOrderRepository;

    @Autowired
    private BaseOrderPositionRepository baseOrderPositionRepository;

    @Override
    public List<Order> findAll() {
        return baseOrderRepository.findAll();
    }

    @Override
    public List<Order> findAllOrdersByUserId(int userId) {
        return baseOrderRepository.findAllOrdersByUserId(userId);
    }

    @Override
    public List<Order> findAllOrdersByMarketId(int marketId) {
        return baseOrderRepository.findAllOrdersByMarketId(marketId);
    }

    @Override
    public List<OrderPosition> findOrderPositionsByProductId(int productId) {
        return baseOrderPositionRepository.findAllOrderPositionsByProductId(productId);
    }

    @Override
    public Optional<Order> findById(int id) {
        return baseOrderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return baseOrderRepository.save(order);
    }

    @Override
    public OrderPosition saveOrderPosition(OrderPosition orderPosition) {
        return baseOrderPositionRepository.save(orderPosition);
    }

    @Override
    public Optional<Order> update(Order order) {
        return Optional.of(baseOrderRepository.save(order));
    }

    @Override
    public Optional<OrderPosition> updateOrderPosition(OrderPosition orderPosition) {
        return Optional.of(baseOrderPositionRepository.save(orderPosition));
    }
}

@Repository
interface BaseOrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "select order from Order order join order.user user where user.id = :user_id")
    List<Order> findAllOrdersByUserId(@Param(value = "user_id") int userId);

    @Query(value = "select order from Order order join order.market market where market.id = :market_id")
    List<Order> findAllOrdersByMarketId(@Param(value = "market_id") int marketId);
}

@Repository
interface BaseOrderPositionRepository extends JpaRepository<OrderPosition, Integer> {
    @Query(value = "select order_position from OrderPosition order_position join order_position.product product where product.id = :product_id")
    List<OrderPosition> findAllOrderPositionsByProductId(@Param("product_id") int productId);
}
