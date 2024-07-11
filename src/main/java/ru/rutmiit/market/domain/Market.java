package ru.rutmiit.market.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "markets")
public class Market extends BaseEntity {
    private String name;
    private String description;
    private List<MarketProduct> products;
    private List<Order> orders;

    public Market(String name, String description, List<MarketProduct> products, List<Order> orders) {
        this.name = name;
        this.description = description;
        this.products = products;
        this.orders = orders;
    }

    protected Market() {}

    @Column(name = "name", length = 127)
    public String getName() {
        return name;
    }

    @Column(name = "description", length = 2047)
    public String getDescription() {
        return description;
    }

    @OneToMany()
    public List<MarketProduct> getProducts() {
        return products;
    }

    @OneToMany
    public List<Order> getOrders() {
        return orders;
    }
}
