package com.emp.shared.dao;

import com.emp.shared.models.Order;

public interface OrderDao {
    void saveOrder(Order order);
}