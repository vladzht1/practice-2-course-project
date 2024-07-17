package ru.rutmiit.market.dtos.internal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.rutmiit.market.domain.ProductCategory;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class MarketReportUnitDto {
    private String productTitle;
    private String productDescription;
    private ProductCategory category;
    private double income;
    private int amountSold;
}
