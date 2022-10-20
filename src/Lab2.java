import java.sql.*;
import java.util.List;

import classes.Order;
import service.OnlineMarketService;

public class Lab2 {
    public static void main(String[] args) throws Exception {
        OnlineMarketService marketService = new OnlineMarketService();
        Order order = new Order();

        order.setDate("Июль");
        order.setDeliveryDuration(10);
        order.setPrice(69000);

        try {
            System.out.println("Таблица до изменений:\n");
            List<Order> orderList = marketService.getAll();
            for (Order o : orderList) {
                System.out.println(o);
            }

            marketService.add(order);
            System.out.println("\nТаблица после добавления новой строчки(" + order + "):\n");
            orderList = marketService.getAll();
            for (Order o : orderList) {
                System.out.println(o);
            }

            int id = 5;
            System.out.println("Экземпляр таблицы с индексом " + id + ": " + marketService.getById(id));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
