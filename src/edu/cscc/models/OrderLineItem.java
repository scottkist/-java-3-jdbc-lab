package edu.cscc.models;

import java.util.Objects;

public class OrderLineItem {

    private Integer id;
    private Integer orderId;
    private Rental rental;

    public OrderLineItem(Integer id, Integer orderId, Rental rental) {
        this.id = id;
        this.orderId = orderId;
        this.rental = rental;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLineItem that = (OrderLineItem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(rental, that.rental);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, rental);
    }

    @Override
    public String toString() {
        return "OrderLineItem{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", rental=" + rental +
                '}';
    }
}
