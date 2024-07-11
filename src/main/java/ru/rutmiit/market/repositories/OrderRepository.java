package ru.rutmiit.market.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.rutmiit.market.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "select order from Order as order join order.user as user where user.id = :user_id")
    List<Order> findByUserId(@Param(value = "user_id") Integer userId);
}
