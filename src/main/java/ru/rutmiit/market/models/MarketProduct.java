package ru.rutmiit.market.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "market_products")
public class MarketProduct extends BaseEntity {
    private Product product;
    private Market market;
    private double price;
    private long quantity;

    public MarketProduct(Product product, Market market, double price, long quantity) {
        this.product = product;
        this.market = market;
        this.price = price;
        this.quantity = quantity;
    }

    protected MarketProduct() {}

    @ManyToOne()
    public Product getProduct() {
        return product;
    }

    @ManyToOne()
    public Market getMarket() {
        return market;
    }

    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    @Column(name = "quantity")
    public long getQuantity() {
        return quantity;
    }
}
