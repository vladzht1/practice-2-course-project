package ru.rutmiit.market.dtos.api;

public class UpdateMarketProductDto {
    private int id;
    private int marketId;
    private int productId;
    private double price;
    private int quantity;

    public UpdateMarketProductDto(int id, int marketId, int productId, double price, int quantity) {
        this.id = id;
        this.marketId = marketId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    protected UpdateMarketProductDto() {}

    public int getId() {
        return id;
    }

    public int getMarketId() {
        return marketId;
    }

    public int getProductId() {
        return productId;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
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

    public void setPrice(double updatedPrice) {
        price = updatedPrice;
    }

    public void setQuantity(int updatedQuantity) {
        quantity = updatedQuantity;
    }
}
