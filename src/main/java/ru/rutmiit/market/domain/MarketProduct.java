package ru.rutmiit.market.domain;

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
    private int quantity;

    public MarketProduct(Product product, Market market, double price, int quantity) {
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
    public int getQuantity() {
        return quantity;
    }

    public void setProduct(Product updatedProduct) {
        product = updatedProduct;
    }

    public void setMarket(Market updatedMarket) {
        market = updatedMarket;
    }

    public void setPrice(double updatedPrice) {
        if (updatedPrice <= 0) {
            return;
        }

        price = updatedPrice;
    }

    public void setQuantity(int updatedQuantity) {
        if (updatedQuantity <= 0) {
            return;
        }

        quantity = updatedQuantity;
    }
}
