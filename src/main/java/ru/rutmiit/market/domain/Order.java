package ru.rutmiit.market.domain;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    private User user;
    private Market market;
    private Date createdAt;
    private Date updatedAt;
    private OrderStatus status;
    private List<OrderPosition> orderPositions;

    public Order(User user, Market market, List<OrderPosition> orderPositions) {
        this.user = user;
        this.market = market;
        this.orderPositions = orderPositions;
        this.status = OrderStatus.CREATED;

        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public Order(User user, Market market) {
        this(user, market, new ArrayList<>());
    }

    protected Order() {}

    @ManyToOne()
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    @ManyToOne()
    @JoinColumn(name = "market_id")
    public Market getMarket() {
        return market;
    }

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    public OrderStatus getStatus() {
        return status;
    }

    @OneToMany(fetch = FetchType.EAGER)
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

    public void addOrderPosition(OrderPosition orderPosition) {
        orderPositions.add(orderPosition);
    }

    public void setMarket(Market updatedMarket) {
        market = updatedMarket;
        markAsUpdated();
    }

    public void setStatus(OrderStatus updatedStatus) {
        status = updatedStatus;
        markAsUpdated();
    }

    public void setOrderPositions(List<OrderPosition> updatedOrderPositions) {
        orderPositions = updatedOrderPositions;
        markAsUpdated();
    }

    public void setUser(User updatedUser) {
        user = updatedUser;
    }

    public void setCreationDate(Date updatedCreationDate) {
        createdAt = updatedCreationDate;
    }

    public void setLastUpdatedDate(Date updateDate) {
        updatedAt = updateDate;
    }

    private void markAsUpdated() {
        updatedAt = new Date();
    }
}
