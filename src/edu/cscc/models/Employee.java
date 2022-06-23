package edu.cscc.models;

import java.util.Objects;

public class Employee extends Person {
    private String activeStoreNumber;

    public Employee(String firstName, String lastName, String activeStoreNumber) {
        super(firstName, lastName);
        this.activeStoreNumber = activeStoreNumber;
    }

    public Employee(Integer id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    public String getActiveStoreNumber() {
        return activeStoreNumber;
    }

    public void setActiveStoreNumber(String activeStoreNumber) {
        this.activeStoreNumber = activeStoreNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(activeStoreNumber, employee.activeStoreNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), activeStoreNumber);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "activeStoreNumber='" + activeStoreNumber + '\'' +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
