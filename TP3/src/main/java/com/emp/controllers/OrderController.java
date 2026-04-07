package com.emp.controllers;

import com.emp.services.OrderService;
import com.emp.shared.models.Order;

public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void createOrder(Order order) {
        orderService.createOrder(order);
    }
}