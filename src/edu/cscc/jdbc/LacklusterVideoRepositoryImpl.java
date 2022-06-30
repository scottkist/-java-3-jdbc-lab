package edu.cscc.jdbc;

import edu.cscc.exceptions.LacklusterVideoServiceException;
import edu.cscc.models.*;
import edu.cscc.services.LacklusterVideoRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LacklusterVideoRepositoryImpl implements LacklusterVideoRepository {
    private final DataSource dataSource;

    public LacklusterVideoRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Order> getOrders() throws LacklusterVideoServiceException {
        List<Order> orders = new ArrayList<>();
        List<OrderLineItem> orderLineItemsTempList = new ArrayList<>();
        try {
            String sql = "SELECT o.id, o.store_number, e.id, e.first_name, e.last_name, e.active_store_number,c.id, c.first_name, c.last_name, c.smart_id from lackluster_video.orders o\n" +
                    "INNER JOIN lackluster_video.employees e ON o.employee_id = e.id\n" +
                    "INNER JOIN lackluster_video.customers c ON o.customer_id = c.id";
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int orderId = resultSet.getInt("o.id");
                Employee employee = new Employee(resultSet.getString("e.first_name"), resultSet.getString("e.last_name"), resultSet.getString("e.active_store_number"));
                Customer customer = new Customer(resultSet.getString("c.first_name"), resultSet.getString("c.last_name"), resultSet.getString("c.smart_id"));
                String storeNumber = resultSet.getString("o.store_number");
                Order order = new Order(orderId, employee, customer, storeNumber);
                orders.add(order);
                //Populate OrderLineItems
                String sqlSelectOrderLineItems = "SELECT ol.id, ol.order_id, ol.rental_id, r.id, r.name FROM lackluster_video.order_line_items ol\n" +
                        "INNER JOIN lackluster_video.rentals r ON ol.rental_id = r.id";
                PreparedStatement preparedStatement2 = connection.prepareStatement(sqlSelectOrderLineItems);
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                while (resultSet2.next()) {
                    int orderLineItemId = resultSet2.getInt("ol.id");
                    int oLiOrderId = resultSet2.getInt("ol.order_id");
                    int oLiRentalId = resultSet2.getInt("ol.rental_id");
                    String rentalName = resultSet2.getString("r.name");
                    Rental rentalObject = new Rental(oLiRentalId, rentalName);
                    OrderLineItem orderLineItem = new OrderLineItem(orderLineItemId, oLiOrderId, rentalObject);
                    orderLineItemsTempList.add(orderLineItem);
                    order.setOrderLineItems(orderLineItemsTempList);
                }
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new LacklusterVideoServiceException("Could not retrieve orders");
        }

        return orders;
    }

    @Override
    public void createOrder(Integer employeeId, Integer customerId, List<Integer> rentalIds) throws LacklusterVideoServiceException {
//        Order(Integer id, Employee employee, Customer customer, String storeNumber)
        String defaultStoreNumber = "39458";
        try {
            String sql = "insert into lackluster_video.orders (employee_id, customer_id, store_number) values (?, ?, ?)";
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setInt(2, customerId);
            preparedStatement.setString(3, defaultStoreNumber);
            preparedStatement.executeUpdate();
            connection.commit();  //this may not be the right spot for this. need to test.   --ORDER IS CREATED NOW--
            //Need to then query for the Order id to get create OrderLineItems..


            //TODO: OrderLineItems (id, order_id, rental_id)
            String queryForOrderId = "SELECT id FROM lackluster_video.orders ORDER BY id DESC LIMIT 1";
            PreparedStatement preparedStatementOrderLineItem = connection.prepareStatement(queryForOrderId);
            ResultSet resultSet = preparedStatementOrderLineItem.executeQuery();
            while (resultSet.next()) {
                int orderId = resultSet.getInt("id");
                for (Integer rentalId : rentalIds) {
                    System.out.println("test");
//                    String sqlCreateOLI = "insert into lackluster_video.order_line_items (order_id, rental_id) values (?, ?)";
//                    PreparedStatement addToOrderLineItemsTable = connection.prepareStatement(sqlCreateOLI);
//                    addToOrderLineItemsTable.setInt(1, orderId);
//                    addToOrderLineItemsTable.setInt(2, rentalId);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new LacklusterVideoServiceException("Could not create order");
        }
    }

    @Override
    public void deleteOrders() throws LacklusterVideoServiceException {
        try {
            String sqlDeleteOrderLineItems = "delete from order_line_items";
            String sqlDeleteOrders = "delete from orders";
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteOrderLineItems);
            preparedStatement.executeUpdate();
            PreparedStatement preparedStatement2 = connection.prepareStatement(sqlDeleteOrders);
            preparedStatement2.executeUpdate();
            connection.commit();
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new LacklusterVideoServiceException("Could not delete orders.");
        }
    }

}
