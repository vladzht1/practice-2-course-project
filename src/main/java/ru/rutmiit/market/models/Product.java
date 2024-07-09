package ru.rutmiit.market.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    private String _title;
    private String _description;

    public Product(String title, String description) {
        _title = title;
        _description = description;
    }

    protected Product() {}

    @Column(name = "title", length = 511)
    public String getTitle() {
        return _title;
    }

    @Column(name = "description", length = 2047)
    public String getDescription() {
        return _description;
    }

    // TODO: Transform setters into domain-based methods

    public void setTitle(String title) {
        _title = title;
    }

    public void setDescription(String description) {
        _description = description;
    }
}
