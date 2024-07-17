package ru.rutmiit.market.dtos.api;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class AddOrderDto {
    private int userId;
    private List<AddOrderPositionDto> products;
}
