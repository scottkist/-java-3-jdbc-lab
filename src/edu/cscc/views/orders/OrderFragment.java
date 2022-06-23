package edu.cscc.views.orders;

import edu.cscc.models.Order;

public class OrderFragment {
    private final Order order;

    public OrderFragment(Order order) {
        this.order = order;
    }

    public void show() {
        System.out.println("Order:");
        System.out.println("**************");
        System.out.println("Order ID: " + order.getId());
        System.out.println("Employee Name: " + order.getEmployee().getName());
        System.out.println("Customer Smart ID: " + order.getCustomer().getSmartId());
        System.out.println("Customer Name: " + order.getCustomer().getName());
        System.out.println("Store Number: " + order.getStoreNumber());
        order.getOrderLineItems().forEach(orderLineItem -> {
            System.out.println("Rental Name: " + orderLineItem.getRental().getName());
        });
        System.out.println();
    }
}
