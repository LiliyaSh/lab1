package dao;

import classes.Order;

import java.sql.SQLException;
import java.util.List;

public interface OnlineMarketDAO {

    void add(Order order) throws SQLException;

    List<Order> getAll() throws SQLException;

    Order getById(int id) throws SQLException;
}
