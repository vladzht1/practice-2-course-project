package ru.rutmiit.market.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String _firstName;
    private String _middleName;
    private String _lastName;
    private String _email;
    private List<Order> _orders;

    public User(String firstName, String middleName, String lastName, String email) {
        _firstName = firstName;
        _middleName = middleName;
        _lastName = lastName;
        _email = email;
        _orders = new ArrayList<>();
    }

    protected User() {}

    @Column(name = "first_name", length = 255)
    public String getFirstName() {
        return _firstName;
    }

    @Column(name = "middle_name", length = 255)
    public String getMiddleName() {
        return _middleName;
    }

    @Column(name = "last_name", length = 255)
    public String getLastName() {
        return _lastName;
    }

    @Column(name = "email", length = 255)
    public String getEmail() {
        return _email;
    }

    @OneToMany(fetch = FetchType.LAZY)
    public List<Order> getOrders() {
        return _orders;
    }

    // TODO: Transform setters into domain-based methods

    public void setFirstName(String firstName) {
        _firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        _middleName = middleName;
    }

    public void setLastName(String lastName) {
        _lastName = lastName;
    }

    public void setEmail(String email) {
        _email = email;
    }

    // FIXME: There should be no setter for `Orders`
    public void setOrders(List<Order> orders) {
        _orders = orders;
    }
}
