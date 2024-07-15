package ru.rutmiit.market.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDto {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private boolean isActive;

    public UserDto(int id, String firstName, String middleName, String lastName, String email, boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.isActive = isActive;
    }

    protected UserDto() {}

    @NotNull(message = "Id must be provided")
    public int getId() {
        return id;
    }

    @NotNull(message = "First name must be provided")
    @NotEmpty(message = "First name must not be empty")
    @Size(min = 2, message = "First name must contain at least 2 characters")
    public String getFirstName() {
        return firstName;
    }

    @NotNull(message = "Middle name must be provided")
    @NotEmpty(message = "Middle name must not be empty")
    @Size(min = 2, message = "Middle name must contain at least 2 characters")
    public String getMiddleName() {
        return middleName;
    }

    @NotNull(message = "Last name must be provided")
    @NotEmpty(message = "Last name must not be empty")
    @Size(min = 2, message = "Last name must contain at least 2 characters")
    public String getLastName() {
        return lastName;
    }

    @NotNull(message = "Email must be provided")
    @NotEmpty(message = "Email must not be empty")
    @Size(min = 5, message = "Email must contain at least 5 characters")
    public String getEmail() {
        return email;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setId(int updatedId) {
        id = updatedId;
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
