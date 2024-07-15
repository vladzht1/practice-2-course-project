package ru.rutmiit.market.dtos.api;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AddUserDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private boolean isActive;

    public AddUserDto(String firstName, String middleName, String lastName, String email) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.isActive = true;
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
}
