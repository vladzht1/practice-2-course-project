package ru.rutmiit.market.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "markets")
public class Market extends BaseEntity {
    private String _name;
    private String _description;
    private List<MarketProduct> _products;
    private List<Order> _orders;

    public Market(String name, String description, List<MarketProduct> products, List<Order> orders) {
        _name = name;
        _description = description;
        _products = products;
        _orders = orders;
    }

    // This constructor is required for compatibility with ORM
    @SuppressWarnings("unused")
    private Market() {}

    @Column(name = "name", length = 127)
    public String getName() {
        return _name;
    }

    @Column(name = "description", length = 2047)
    public String getDescription() {
        return _description;
    }

    @OneToMany()
    public List<MarketProduct> getProducts() {
        return _products;
    }

    @OneToMany
    public List<Order> getOrders() {
        return _orders;
    }
}
