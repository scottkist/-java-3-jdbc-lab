package edu.cscc.controllers;

import edu.cscc.exceptions.LacklusterVideoServiceException;
import edu.cscc.models.Order;
import edu.cscc.mvc.framework.ApplicationController;
import edu.cscc.mvc.framework.MVCContext;
import edu.cscc.services.LacklusterVideoRepository;
import edu.cscc.views.ErrorView;
import edu.cscc.views.orders.OrderCreateView;
import edu.cscc.views.orders.OrdersIndexView;

import java.util.List;
import java.util.Map;

public class OrdersController extends ApplicationController {
    private LacklusterVideoRepository lacklusterVideoRepository;

    public OrdersController(MVCContext context) {
        super(context);

        // TODO Create and assign an instance of the LacklusterVideoRepository
        //  to the lacklusterVideoRepository variable.
    }

    public void index() {
        try {
            List<Order> orders = lacklusterVideoRepository.getOrders();
            render(new OrdersIndexView(context, orders));
        } catch (LacklusterVideoServiceException e) {
            e.printStackTrace();
            render(new ErrorView(context, e.getMessage()));
        }
    }

    public void create() {
        render(new OrderCreateView(context));
    }

    public void save() {
        Map<String, Object> params = context.getRequest().getParams();
        try {
            Integer employeeId = (Integer) params.get("employeeId");
            System.out.println("Employee id: " + employeeId);
            Integer customerId = (Integer) params.get("customerId");
            List<Integer> rentalIds = (List<Integer>) params.get("rentalIds");
            lacklusterVideoRepository.createOrder(employeeId, customerId, rentalIds);
            List<Order> orders = lacklusterVideoRepository.getOrders();
            render(new OrdersIndexView(context, orders));
        } catch(LacklusterVideoServiceException ex) {
            ex.printStackTrace();
            render(new ErrorView(context, ex.getMessage()));
        }
    }

    public void delete() {
        try {
            lacklusterVideoRepository.deleteOrders();
            List<Order> orders = lacklusterVideoRepository.getOrders();
            render(new OrdersIndexView(context, orders));
        } catch (LacklusterVideoServiceException e) {
            e.printStackTrace();
            render(new ErrorView(context, e.getMessage()));
        }
    }
}
