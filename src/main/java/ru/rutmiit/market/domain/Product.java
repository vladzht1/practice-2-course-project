package ru.rutmiit.market.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    private String title;
    private String description;

    public Product(String title, String description) {
        this.title = title;
        this.description = description;
    }

    protected Product() {}

    @Column(name = "title", length = 512)
    public String getTitle() {
        return title;
    }

    @Column(name = "description", length = 2048)
    public String getDescription() {
        return description;
    }

    public void setTitle(String updatedTitle) {
        title = updatedTitle;
    }

    public void setDescription(String updatedDescription) {
        description = updatedDescription;
    }
}
