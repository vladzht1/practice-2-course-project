package ru.rutmiit.market.dtos.api;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateUserDto {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private boolean isActive;

    public UpdateUserDto(int id, String firstName, String middleName, String lastName, String email, boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.isActive = isActive;
    }

    @NotNull(message = "User id must not be null")
    @Min(value = 1, message = "User id cannot be negative or zero")
    public int getId() {
        return id;
    }

    @NotEmpty(message = "First name must not be empty")
    @Size(min = 2, message = "First name must contain at least 2 characters")
    public String getFirstName() {
        return firstName;
    }

    @NotEmpty(message = "Middle name must not be empty")
    @Size(min = 2, message = "Middle name must contain at least 2 characters")
    public String getMiddleName() {
        return middleName;
    }

    @NotEmpty(message = "Last name must not be empty")
    @Size(min = 2, message = "Last name must contain at least 2 characters")
    public String getLastName() {
        return lastName;
    }

    @NotEmpty(message = "Email must not be empty")
    @Size(min = 5, message = "Email must contain at least 5 characters")
    public String getEmail() {
        return email;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setFirstName(String updatedFirstName) {
        firstName = updatedFirstName;
    }

    public void setMiddleName(String updatedMiddleName) {
        middleName = updatedMiddleName;
    }

    public void setLastName(String updatedLastName) {
        lastName = updatedLastName;
    }

    public void setEmail(String updatedEmail) {
        email = updatedEmail;
    }

    public void setIsActive(boolean updatedIsActive) {
        isActive = updatedIsActive;
    }
}
