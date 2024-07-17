package ru.rutmiit.market.dtos.api;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class UpdateUserDto {
    @NotNull(message = "User id must not be null")
    @Min(value = 1, message = "User id cannot be negative or zero")
    private int id;

    @NotEmpty(message = "First name must not be empty")
    @Size(min = 2, message = "First name must contain at least 2 characters")
    private String firstName;

    @NotEmpty(message = "Middle name must not be empty")
    @Size(min = 2, message = "Middle name must contain at least 2 characters")
    private String middleName;

    @NotEmpty(message = "Last name must not be empty")
    @Size(min = 2, message = "Last name must contain at least 2 characters")
    private String lastName;

    @NotEmpty(message = "Email must not be empty")
    @Size(min = 5, message = "Email must contain at least 5 characters")
    private String email;
}
