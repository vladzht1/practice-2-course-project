package ru.rutmiit.market.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    private String title;
    private String description;
    private ProductCategory productCategory;

    public Product(String title, String description, ProductCategory productCategory) {
        this.title = title;
        this.description = description;
        this.productCategory = productCategory;
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

    @Column(name = "product_category")
    @Enumerated(EnumType.STRING)
    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setTitle(String updatedTitle) {
        if (updatedTitle == null) {
            return;
        }

        title = updatedTitle;
    }

    public void setDescription(String updatedDescription) {
        if (updatedDescription == null) {
            return;
        }

        description = updatedDescription;
    }

    public void setProductCategory(ProductCategory updatedProductCategory) {
        if (updatedProductCategory == null) {
            return;
        }

        productCategory = updatedProductCategory;
    }
}
