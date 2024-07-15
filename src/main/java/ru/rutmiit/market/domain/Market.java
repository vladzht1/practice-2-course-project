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

    @Column(name = "name", length = 128)
    public String getName() {
        return name;
    }

    @Column(name = "description", length = 2048)
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

    public void setName(String updatedName) {
        if (updatedName == null) {
            return;
        }

        name = updatedName;
    }

    public void setDescription(String updatedDescription) {
        if (updatedDescription == null) {
            return;
        }

        description = updatedDescription;
    }

    public void setProducts(List<MarketProduct> updatedProducts) {
        if (updatedProducts == null) {
            return;
        }

        products = updatedProducts;
    }

    public void setOrders(List<Order> updatedOrders) {
        if (updatedOrders == null) {
            return;
        }

        orders = updatedOrders;
    }
}
