package ru.rutmiit.market.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import ru.rutmiit.market.domain.ProductCategory;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class OrderPositionDto {
    private String title;
    private String description;
    private ProductCategory category;
    private double price;
    private double discount;
    private int quantity;
    private double totalPrice;
}
