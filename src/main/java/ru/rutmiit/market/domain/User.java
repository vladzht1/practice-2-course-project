package ru.rutmiit.market.domain;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private boolean isActive;
    private List<Order> orders;

    public User(String firstName, String middleName, String lastName, String email) {
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        setEmail(email);
        setIsActive(true);
        setOrders(new ArrayList<>());
    }

    public User(String firstName, String middleName, String lastName, String email, List<Order> orders) {
        this(firstName, middleName, lastName, email);
        setOrders(orders);
    }

    protected User() {}

    @Column(name = "first_name", length = 256)
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "middle_name", length = 256)
    public String getMiddleName() {
        return middleName;
    }

    @Column(name = "last_name", length = 256)
    public String getLastName() {
        return lastName;
    }

    @Column(name = "email", length = 256)
    public String getEmail() {
        return email;
    }

    @Column(name = "is_active")
    public boolean getIsActive() {
        return isActive;
    }

    @OneToMany(fetch = FetchType.LAZY)
    public List<Order> getOrders() {
        return orders;
    }

    public void setFirstName(String updatedFirstName) {
        if (updatedFirstName == null) {
            return;
        }

        firstName = updatedFirstName;
    }

    public void setMiddleName(String updatedMiddleName) {
        if (updatedMiddleName == null) {
            return;
        }

        middleName = updatedMiddleName;
    }

    public void setLastName(String updatedLastName) {
        if (updatedLastName == null) {
            return;
        }

        lastName = updatedLastName;
    }

    public void setEmail(String updatedEmail) {
        if (updatedEmail == null) {
            return;
        }

        email = updatedEmail;
    }

    public void setIsActive(boolean updatedActive) {
        isActive = updatedActive;
    }

    // FIXME: There should be no setter for `Orders`
    public void setOrders(List<Order> updatedOrders) {
        orders = updatedOrders;
    }
}
