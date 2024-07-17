package ru.rutmiit.market.dtos.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class UpdateMarketProductDto {
    private int id;
    private int marketId;
    private int productId;
    private double price;
    private int quantity;
}
