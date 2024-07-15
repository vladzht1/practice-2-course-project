package ru.rutmiit.market.dtos.api;

public class AddMarketProductDto {
    private int marketId;
    private int productId;
    private double price;
    private int quantity;

    public AddMarketProductDto(int marketId, int productId, double price, int quantity) {
        this.marketId = marketId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    protected AddMarketProductDto() {}

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
