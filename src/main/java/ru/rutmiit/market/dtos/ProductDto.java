package ru.rutmiit.market.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import ru.rutmiit.market.domain.ProductCategory;

public class ProductDto {
    private int id;
    private String title;
    private String description;
    private ProductCategory productCategory;

    public ProductDto(int id, String title, String description, ProductCategory productCategory) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.productCategory = productCategory;
    }

    protected ProductDto() {}

    @NotNull(message = "Id must be provided")
    public int getId() {
        return id;
    }

    @NotNull(message = "Title must be provided")
    @NotEmpty(message = "Title must not be empty")
    @Size(min = 5, message = "Title must contain at least 5 characters")
    public String getTitle() {
        return title;
    }

    @NotNull(message = "Description must be provided")
    @NotEmpty(message = "Description must not be empty")
    @Size(min = 50, message = "Description must contain at least 50 characters")
    public String getDescription() {
        return description;
    }

    @NotNull(message = "Product category must be provided")
    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setId(int updatedId) {
        id = updatedId;
    }

    public void setTitle(String updatedTitle) {
        title = updatedTitle;
    }

    public void setDescription(String updatedDescription) {
        description = updatedDescription;
    }

    public void setProductCategory(ProductCategory updatedProductCategory) {
        productCategory = updatedProductCategory;
    }
}
