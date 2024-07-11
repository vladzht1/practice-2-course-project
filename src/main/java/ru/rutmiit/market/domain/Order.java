package ru.rutmiit.market.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    private Market market;
    private Date createdAt;
    private Date updatedAt;
    private OrderStatus status;
    private List<OrderPosition> orderPositions;

    public Order(Market market, List<OrderPosition> orderPositions) {
        this.market = market;
        this.orderPositions = orderPositions;
        this.status = OrderStatus.CREATED;

        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    protected Order() {}

    @ManyToOne()
    public Market getMarket() {
        return market;
    }

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    public OrderStatus getStatus() {
        return status;
    }

    @OneToMany()
    public List<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    @Column(name = "created_at")
    public Date getCreationDate() {
        return createdAt;
    }

    @Column(name = "updated_at")
    public Date getLastUpdatedDate() {
        return updatedAt;
    }

    public void setMarket(Market updatedMarket) {
        market = updatedMarket;
        markAsUpdated();
    }

    public void setStatus(OrderStatus updatedStatus) {
        status = updatedStatus;
        markAsUpdated();
    }

    // FIXME: This setter must have validation
    public void setOrderPositions(List<OrderPosition> updatedOrderPositions) {
        orderPositions = updatedOrderPositions;
        markAsUpdated();
    }

    private void markAsUpdated() {
        updatedAt = new Date();
    }
}
