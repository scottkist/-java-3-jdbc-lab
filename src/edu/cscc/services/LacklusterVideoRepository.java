package edu.cscc.services;

import edu.cscc.exceptions.LacklusterVideoServiceException;
import edu.cscc.models.Order;

import javax.sql.DataSource;
import java.util.List;

public interface LacklusterVideoRepository {

    /**
     * This method returns all created orders, including their associated OrderLineItems.
     * If any error occurs when communicating with the database this method will throw
     * a LacklusterVideoServiceException.
     * @return
     * @throws LacklusterVideoServiceException
     */
    List<Order> getOrders() throws LacklusterVideoServiceException;

    /**
     * Creates a new order, which is composed of a number of rentals.
     *
     * If any error occurs when communicating with the database this method will
     * throw a LacklusterVideoServiceException.
     *
     * @param employeeId The id of the employee creating the order.
     * @param customerId The id of the customer renting videos.
     * @param rentalIds The list of rental ids for the order.
     * @throws LacklusterVideoServiceException
     */
    void createOrder(Integer employeeId, Integer customerId, List<Integer> rentalIds) throws LacklusterVideoServiceException;

    /**
     * Deletes all orders.
     *
     * If any error occurs when communicating with the database this method will
     * throw a LacklusterVideoServiceException.
     * @throws LacklusterVideoServiceException
     */
    void deleteOrders() throws LacklusterVideoServiceException;
}
