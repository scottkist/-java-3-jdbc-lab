package edu.cscc.models;

import java.util.Objects;

public class Customer extends Person {

    private String smartId;

    public Customer(String firstName, String lastName, String smartId) {
        super(firstName, lastName);
        this.smartId = smartId;
    }

    public Customer(Integer id, String firstName, String lastName, String smartId) {
        super(id, firstName, lastName);
        this.smartId = smartId;
    }

    public String getSmartId() {
        return smartId;
    }

    public void setSmartId(String smartId) {
        this.smartId = smartId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(smartId, customer.smartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), smartId);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "smartId='" + smartId + '\'' +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
