package ru.rutmiit.market.dtos;

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
public class UserDto {
    @NotNull(message = "Id must be provided")
    private int id;

    @NotNull(message = "First name must be provided")
    @NotEmpty(message = "First name must not be empty")
    @Size(min = 2, message = "First name must contain at least 2 characters")
    private String firstName;

    @NotNull(message = "Middle name must be provided")
    @NotEmpty(message = "Middle name must not be empty")
    @Size(min = 2, message = "Middle name must contain at least 2 characters")
    private String middleName;

    @NotNull(message = "Last name must be provided")
    @NotEmpty(message = "Last name must not be empty")
    @Size(min = 2, message = "Last name must contain at least 2 characters")
    private String lastName;

    @NotNull(message = "Email must be provided")
    @NotEmpty(message = "Email must not be empty")
    @Size(min = 5, message = "Email must contain at least 5 characters")
    private String email;

    private boolean isActive;
}
