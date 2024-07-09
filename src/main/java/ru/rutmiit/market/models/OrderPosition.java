package ru.rutmiit.market.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_positions")
public class OrderPosition extends BaseEntity {
    private Order _order;
    private double _price;
    private int _quantity;

    public OrderPosition(Order order, double price, int quantity) {
        _order = order;
        _price = price;
        _quantity = quantity;
    }

    // This constructor is required for compatibility with ORM
    @SuppressWarnings("unused")
    private OrderPosition() {}

    @OneToOne()
    public Order getOrder() {
        return _order;
    }

    @Column(name = "price")
    public double getPrice() {
        return _price;
    }

    @Column(name = "quantity")
    public int getQuantity() {
        return _quantity;
    }

    // NOTE: This model is IMMUTABLE so there should be no setters
}
