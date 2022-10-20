package service;

import helper.Helper;
import dao.OnlineMarketDAO;
import classes.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OnlineMarketService extends Helper implements OnlineMarketDAO{

    private final Connection connection = getConnection();

    @Override
    public void add(Order order) throws SQLException {
        PreparedStatement statement = null;

        String sql = "INSERT INTO onlinemarket (date, delivery_duration, price) VALUES(?, ?, ?)";

        try {
            statement = connection.prepareStatement(sql);

            statement.setString(1, order.getDate());
            statement.setInt(2, order.getDeliveryDuration());
            statement.setInt(3, order.getPrice());

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
    public List<Order> getAll() throws SQLException {
        List<Order> orderList = new ArrayList<>();

        String sql = "SELECT * FROM onlinemarket";

        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setDate(resultSet.getString("date"));
                order.setDeliveryDuration(resultSet.getInt("delivery_duration"));
                order.setPrice(resultSet.getInt("price"));

                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return orderList;
    }

    @Override
    public Order getById(int id) throws SQLException {
        PreparedStatement statement = null;

        String sql = "SELECT * FROM onlinemarket WHERE id=?";

        Order order = new Order();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                order.setId(resultSet.getInt("id"));
                order.setDate(resultSet.getString("date"));
                order.setDeliveryDuration(resultSet.getInt("delivery_duration"));
                order.setPrice(resultSet.getInt("price"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return order;
    }
}
