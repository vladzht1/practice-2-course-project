package ru.rutmiit.market.dtos;

import ru.rutmiit.market.domain.Market;
import ru.rutmiit.market.domain.Product;
import ru.rutmiit.market.domain.ProductCategory;

public class MarketProductDto {
    private int id;
    private int marketId;
    private int productId;
    private String productTitle;
    private String productDescription;
    private double price;
    private int quantity;
    private ProductCategory productCategory;

    public MarketProductDto(int id, Product product, Market market, double price, int quantity) {
        this.id = id;
        this.marketId = market.getId();
        this.productId = product.getId();
        this.productTitle = product.getTitle();
        this.productDescription = product.getDescription();
        this.price = price;
        this.quantity = quantity;
        this.productCategory = product.getProductCategory();
    }

    protected MarketProductDto() {}

    public int getId() {
        return id;
    }

    public int getMarketId() {
        return marketId;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public ProductCategory getCategory() {
        return productCategory;
    }

    public void setId(int updatedId) {
        id = updatedId;
    }

    public void setMarketId(int updatedMarketId) {
        marketId = updatedMarketId;
    }

    public void setProductId(int updatedProductId) {
        productId = updatedProductId;
    }

    public void setProductTitle(String updatedProductTitle) {
        productTitle = updatedProductTitle;
    }

    public void setProductDescription(String updatedProductDescription) {
        productDescription = updatedProductDescription;
    }

    public void setPrice(double updatedPrice) {
        price = updatedPrice;
    }

    public void setQuantity(int updatedQuantity) {
        quantity = updatedQuantity;
    }

    public void setProductCategory(ProductCategory updatedProductCategory) {
        productCategory = updatedProductCategory;
    }
}
