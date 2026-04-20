package com.emp.services;

import com.emp.shared.dao.OrderDao;
import com.emp.shared.models.Order;

public class SimpleOrderService {
    private final OrderDao orderDao;

    public SimpleOrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Order createOrder(Order order) {
        return orderDao.saveOrder(order);
    }
}
