package ru.rutmiit.market.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_positions")
public class OrderPosition extends BaseEntity {
    private Order order;
    private Product product;
    private double price;
    private double discount;
    private int quantity;

    public OrderPosition(Order order, Product product, double price, int quantity) {
        this.order = order;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderPosition(Order order, Product product, double price, double discount, int quantity) {
        this(order, product, price, quantity);
        this.discount = discount;
    }

    protected OrderPosition() {}

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    public Order getOrder() {
        return order;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    @Column(name = "discount")
    public double getDiscount() {
        return discount;
    }

    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setOrder(Order updatedOrder) {
        order = updatedOrder;
    }

    public void setProduct(Product updatedProduct) {
        product = updatedProduct;
    }

    public void setPrice(double updatedPrice) {
        price = updatedPrice;
    }

    public void setDiscount(double updatedDiscount) {
        discount = updatedDiscount;
    }

    public void setQuantity(int updatedQuantity) {
        quantity = updatedQuantity;
    }
}
