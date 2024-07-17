package ru.rutmiit.market.dtos.internal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rutmiit.market.domain.MarketProduct;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class MarketProductOrderDto {
    private MarketProduct marketProduct;
    private int quantity;
}
