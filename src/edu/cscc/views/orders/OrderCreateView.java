package edu.cscc.views.orders;

import edu.cscc.mvc.framework.ApplicationView;
import edu.cscc.mvc.framework.MVCContext;

import java.util.*;

public class OrderCreateView extends ApplicationView {
    public OrderCreateView(MVCContext context) {
        super(context);
    }

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Create Order:");
        System.out.print("Employee id: ");
        Integer employeeId = scanner.nextInt();

        System.out.print("Customer ID: ");
        Integer customerId = scanner.nextInt();

        List<Integer> rentalIds = new ArrayList<>();
        try {
            AddRentalsView addRentalsView = new AddRentalsView();
            addRentalsView.show(rentalIds);
            while (!addRentalsView.isCompleted()) {
                addRentalsView.show(rentalIds);
            }
            if (addRentalsView.isManageOrders()) {
                route("Orders", "index");
            } else if (addRentalsView.isCompleted()) {
                Map<String, Object> params = new HashMap<>();
                params.put("employeeId", employeeId);
                params.put("customerId", customerId);
                params.put("rentalIds", rentalIds);
                route("Orders", "save", params);
            } else {
                route("Orders", "index");
            }
        } catch (Exception e) {
            route("Orders", "index");
        }
    }
}
