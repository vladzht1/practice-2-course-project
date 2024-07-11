package ru.rutmiit.market.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_positions")
public class OrderPosition extends BaseEntity {
    private Order order;
    private double price;
    private int quantity;

    public OrderPosition(Order order, double price, int quantity) {
        this.order = order;
        this.price = price;
        this.quantity = quantity;
    }

    protected OrderPosition() {}

    @OneToOne()
    public Order getOrder() {
        return order;
    }

    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    // NOTE: This model is IMMUTABLE so there should be no setters
}
