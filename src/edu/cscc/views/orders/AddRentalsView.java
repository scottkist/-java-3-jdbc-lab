package edu.cscc.views.orders;

import java.util.List;
import java.util.Scanner;

public class AddRentalsView {
    private boolean completed;
    private boolean manageOrders;

    public boolean isCompleted() {
        return completed;
    }

    public boolean isManageOrders() { return manageOrders; }

    void show(List<Integer> rentalIds) {
        Scanner scanner = new Scanner(System.in);
            System.out.println("1. Add a rental");
            System.out.println("2. Complete order");
            System.out.println("3. Manage orders");
            System.out.print("Choice: ");
            Integer choice = scanner.nextInt();
            if (choice == 1) {
                System.out.print("Rental ID: ");
                Integer rentalId = scanner.nextInt();
                rentalIds.add(rentalId);
            } else if (choice == 2) {
                this.completed = true;
            } else {
                this.completed = true;
                this.manageOrders = true;
            }
    }
}