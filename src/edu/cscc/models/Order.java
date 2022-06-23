package edu.cscc.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {

    public static final String DEFAULT_STORE_NUMBER = "39458";
    private Integer id;
    private Employee employee;
    private Customer customer;
    private String storeNumber;
    private List<OrderLineItem> orderLineItems;

    public Order(Integer id, Employee employee, Customer customer, String storeNumber) {
        this.id = id;
        this.employee = employee;
        this.customer = customer;
        this.storeNumber = storeNumber;
        this.orderLineItems = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(String storeNumber) {
        this.storeNumber = storeNumber;
    }

    public List<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }

    public void setOrderLineItems(List<OrderLineItem> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(employee, order.employee) &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(storeNumber, order.storeNumber) &&
                Objects.equals(orderLineItems, order.orderLineItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, customer, storeNumber, orderLineItems);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", employee=" + employee +
                ", customer=" + customer +
                ", storeNumber='" + storeNumber + '\'' +
                ", orderLineItems=" + orderLineItems +
                '}';
    }
}
