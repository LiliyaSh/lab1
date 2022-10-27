import java.sql.*;
import java.util.List;

import classes.Customer;
import classes.Order;
import service.CustomerService;
import service.OnlineMarketService;

public class Lab2 {
    public static void main(String[] args) throws Exception {
        OnlineMarketService marketService = new OnlineMarketService();
        Order order = new Order();

        order.setDate("Июль");
        order.setDeliveryDuration(10);
        order.setPrice(69000);

        try {
            System.out.println("Таблица OnlineMarket до изменений:\n");
            List<Order> orderList = marketService.getAll();
            for (Order o : orderList) {
                System.out.println(o);
            }

            marketService.add(order);
            System.out.println("\nТаблица OnlineMarket после добавления новой строчки(" + order + "):\n");
            orderList = marketService.getAll();
            for (Order o : orderList) {
                System.out.println(o);
            }

            int id = 5;
            System.out.println("Экземпляр таблицы OnlineMarket с индексом " + id + ": " + marketService.getById(id));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        CustomerService customerService = new CustomerService();
        Customer customer = new Customer();

        customer.setOrderId(25);
        customer.setCustomerName("Сидоров");

        try {
            System.out.println("Таблица Customer до изменений:\n");
            List<Customer> customerList = customerService.getAll();
            for (Customer c : customerList) {
                System.out.println(c);
            }

            customerService.add(customer);
            System.out.println("\nТаблица Customer после добавления новой строчки(" + customer + "):\n");
            customerList = customerService.getAll();
            for (Customer c : customerList) {
                System.out.println(c);
            }

            String name = "Иванов";
            System.out.println("Экземпляры таблицы Customer с именем " + name + ": ");
            customerList = customerService.getIDsByName(name);
            for (Customer c : customerList) {
                System.out.println(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
