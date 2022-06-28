package edu.cscc.jdbc;

import edu.cscc.exceptions.LacklusterVideoServiceException;
import edu.cscc.models.Customer;
import edu.cscc.models.Employee;
import edu.cscc.models.Order;
import edu.cscc.services.LacklusterVideoRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//
//Order:
//        **************
//        Order ID: 5
//        Employee Name: Wynter Miller
//        Customer Smart ID: 23945808497
//        Customer Name: Dibbert Dibbert
//        Store Number: 39458
//        Rental Name: The Godfather
//        Rental Name: The Shawshank Redemption
//
//        If no orders exist the application displays:
//        No orders found.

public class LacklusterVideoRepositoryImpl implements LacklusterVideoRepository {
    private final DataSource dataSource;

    public LacklusterVideoRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Order> getOrders() throws LacklusterVideoServiceException {
        List<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT * from lackluster_video.orders\n" +
                    "INNER JOIN lackluster_video.employees on lackluster_video.orders.employee_id = lackluster_video.employees.id\n" +
                    "INNER JOIN lackluster_video.customers on lackluster_video.orders.customer_id = lackluster_video.customers.id";
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int orderId = resultSet.getInt(1);
                Employee employee = new Employee(resultSet.getString(6),resultSet.getString(7),resultSet.getString(8));
                Customer customer = new Customer(resultSet.getString(10),resultSet.getString(11),resultSet.getString(12));
                String storeNumber = resultSet.getString(4);
                Order order = new Order(orderId, employee, customer, storeNumber);
                orders.add(order);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new LacklusterVideoServiceException("Could not retrieve orders");
        }

        return orders;
    }

//working on this. not sure how to do this yet.
    @Override
    public void createOrder(Integer employeeId, Integer customerId, List<Integer> rentalIds) throws LacklusterVideoServiceException {
        try {
            String sql = "insert into lackluster_video.orders (employee_id, customer_id, store_number) values (1, 2, '3');";
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,employeeId);
            preparedStatement.setInt(2,customerId);
            preparedStatement.setString();
//            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new LacklusterVideoServiceException("Could not create order");
        }
    }

    @Override
    public void deleteOrders() throws LacklusterVideoServiceException {
        try {
            String sqlDeleteOrderLi = "delete from order_line_items";
            String sqlDeleteOrders = "delete from orders";
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteOrderLi);
            preparedStatement.executeUpdate();
            PreparedStatement preparedStatement2 = connection.prepareStatement(sqlDeleteOrders);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new LacklusterVideoServiceException("Could not delete orders.");
        }
    }

}
