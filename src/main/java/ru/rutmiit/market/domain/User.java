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
    private List<Order> orders;

    public User(String firstName, String middleName, String lastName, String email) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.orders = new ArrayList<>();
    }

    protected User() {}

    @Column(name = "first_name", length = 255)
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "middle_name", length = 255)
    public String getMiddleName() {
        return middleName;
    }

    @Column(name = "last_name", length = 255)
    public String getLastName() {
        return lastName;
    }

    @Column(name = "email", length = 255)
    public String getEmail() {
        return email;
    }

    @OneToMany(fetch = FetchType.LAZY)
    public List<Order> getOrders() {
        return orders;
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

    // FIXME: There should be no setter for `Orders`
    public void setOrders(List<Order> updatedOrders) {
        orders = updatedOrders;
    }
}
