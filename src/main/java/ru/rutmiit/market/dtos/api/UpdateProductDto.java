package ru.rutmiit.market.dtos.api;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
public class UpdateProductDto {
    @NotNull
    private int id;

    @NotEmpty(message = "Title must not be empty")
    @Size(min = 5, message = "Title must contain at least 5 characters")
    private String title;

    @NotEmpty(message = "Description must not be empty")
    @Size(min = 50, message = "Description must contain at least 50 characters")
    private String description;

    @NotEmpty(message = "Product category must be provided")
    private ProductCategory productCategory;
}
