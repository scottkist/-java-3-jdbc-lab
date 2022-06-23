package edu.cscc.views.orders;

import edu.cscc.models.Order;
import edu.cscc.mvc.framework.ApplicationView;
import edu.cscc.mvc.framework.MVCContext;

import java.util.List;
import java.util.Scanner;

public class OrdersIndexView extends ApplicationView {
    private List<Order> orders;

    public OrdersIndexView(MVCContext context, List<Order> orders) {
        super(context);
        this.orders = orders;
    }

    @Override
    public void show() {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            orders.forEach(order -> {
                new OrderFragment(order).show();
            });
        }

        System.out.println("1. Create order");
        System.out.println("2. Delete all orders");
        System.out.println("3. Home");
        System.out.print("Choice: ");
        Scanner scanner = new Scanner(System.in);
        try {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    route("Orders", "create");
                    break;
                case 2:
                    route("Orders", "delete");
                    break;
                case 3:
                    route("Home", "index");
                    break;
                default:
                    route("Orders", "index");
            }
        } catch (Exception exception) {
            route("Orders", "index");
        }
    }
}
