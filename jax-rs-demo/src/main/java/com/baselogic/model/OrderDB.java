package com.baselogic.model;

import java.util.*;
import java.util.Date;

public class OrderDB {
    private static ArrayList<Order> orderDB = null;

    private OrderDB() {
        orderDB = new ArrayList<Order>();
        orderDB.add(new Order(10, "Order 10", new Date()));
        orderDB.add(new Order(20, "Order 20", new Date()));
        orderDB.add(new Order(30, "Order 30", new Date()));
        orderDB.add(new Order(40, "Order 40", new Date()));
    }

    public static List<Order> getInstance() {
        if (orderDB == null) {
            new OrderDB();
        }
        return orderDB;
    }
}
