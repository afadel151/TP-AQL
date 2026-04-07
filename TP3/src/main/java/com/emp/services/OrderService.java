package com.emp.services;

import com.emp.shared.dao.OrderDao;
import com.emp.shared.models.Order;

public class OrderService {

    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void createOrder(Order order) {
        orderDao.saveOrder(order);
    }
}