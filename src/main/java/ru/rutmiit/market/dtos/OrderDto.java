package ru.rutmiit.market.dtos;

import java.util.Date;
import java.util.List;

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
public class OrderDto {
    private int id;
    private int userId;
    private int marketId;
    private Date createdAt;
    private Date updatedAt;
    private OrderStatus status;
    private List<OrderPositionDto> products;
}
