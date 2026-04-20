package com.emp.shared.dao;

import com.emp.shared.models.Order;

public interface OrderDao {
    Order saveOrder(Order order);
}