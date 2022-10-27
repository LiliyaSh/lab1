package service;

import helper.Helper;
import dao.CustomerDAO;
import classes.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerService extends Helper implements CustomerDAO {

    private final Connection connection = getConnection();

    @Override
    public void add(Customer customer) throws SQLException {
        PreparedStatement statement = null;

        String sql = "INSERT INTO customer (order_id, customer_name) VALUES(?, ?)";

        try {
            statement = connection.prepareStatement(sql);

            statement.setInt(1, customer.getOrderId());
            statement.setString(2, customer.getCustomerName());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        List<Customer> customerList = new ArrayList<>();

        String sql = "SELECT * FROM customer";

        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setOrderId(resultSet.getInt("order_id"));
                customer.setCustomerName(resultSet.getString("customer_name"));

                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return customerList;
    }

    @Override
    public List<Customer> getIDsByName(String name) throws SQLException {
        PreparedStatement statement = null;

        String sql = "select * from customer where customer_name like ?";

        List<Customer> customerList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);

            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                while (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setOrderId(resultSet.getInt("order_id"));
                    customer.setCustomerName(resultSet.getString("customer_name"));

                    customerList.add(customer);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return customerList;
    }
}
