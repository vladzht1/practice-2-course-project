package ru.rutmiit.market.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    private Market _market;
    private Date _createdAt;
    private Date _updatedAt;
    private OrderStatus _status;
    private List<OrderPosition> _orderPositions;

    public Order(Market market, List<OrderPosition> orderPositions) {
        _market = market;
        _orderPositions = orderPositions;
        _status = OrderStatus.CREATED;

        _createdAt = new Date();
        _updatedAt = new Date();
    }

    protected Order() {}

    @ManyToOne()
    public Market getMarket() {
        return _market;
    }

    @Column(name = "status")
    public OrderStatus getStatus() {
        return _status;
    }

    @OneToMany()
    public List<OrderPosition> getOrderPositions() {
        return _orderPositions;
    }

    @Column(name = "created_at")
    public Date getCreationDate() {
        return _createdAt;
    }

    @Column(name = "updated_at")
    public Date getLastUpdatedDate() {
        return _updatedAt;
    }

    public void setMarket(Market market) {
        _market = market;
        markAsUpdated();
    }

    public void setStatus(OrderStatus status) {
        _status = status;
        markAsUpdated();
    }

    // FIXME: This setter must have validation
    public void setOrderPositions(List<OrderPosition> orderPositions) {
        _orderPositions = orderPositions;
        markAsUpdated();
    }

    private void markAsUpdated() {
        _updatedAt = new Date();
    }
}
