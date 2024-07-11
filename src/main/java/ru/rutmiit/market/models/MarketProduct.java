package ru.rutmiit.market.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "market_products")
public class MarketProduct extends BaseEntity {
    private Product _product;
    private Market _market;
    private double _price;
    private long _quantity;

    public MarketProduct(Product product, Market market, double price, long quantity) {
        _product = product;
        _market = market;
        _price = price;
        _quantity = quantity;
    }

    protected MarketProduct() {}

    @ManyToOne()
    public Product getProduct() {
        return _product;
    }

    @ManyToOne()
    public Market getMarket() {
        return _market;
    }

    @Column(name = "price")
    public double getPrice() {
        return _price;
    }

    @Column(name = "quantity")
    public long getQuantity() {
        return _quantity;
    }
}
