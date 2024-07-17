package ru.rutmiit.market.dtos.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import ru.rutmiit.market.domain.OrderStatus;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class UpdateOrderDto {
    private int id;
    private OrderStatus status;
}
