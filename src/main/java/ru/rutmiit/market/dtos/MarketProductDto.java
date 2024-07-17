package ru.rutmiit.market.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import ru.rutmiit.market.domain.ProductCategory;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class MarketProductDto {
    private int id;
    private int marketId;
    private int productId;
    private String productTitle;
    private String productDescription;
    private double price;
    private int quantity;
    private ProductCategory productCategory;
}
