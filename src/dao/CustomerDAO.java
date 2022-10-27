package dao;

import classes.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {
    void add(Customer customer) throws SQLException;

    List<Customer> getAll() throws SQLException;

    List<Customer> getIDsByName(String name) throws SQLException;
}
