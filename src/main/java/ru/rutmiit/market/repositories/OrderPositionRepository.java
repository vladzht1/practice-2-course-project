package ru.rutmiit.market.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.rutmiit.market.domain.OrderPosition;

@Repository
public interface OrderPositionRepository extends JpaRepository<OrderPosition, Integer> {
}
