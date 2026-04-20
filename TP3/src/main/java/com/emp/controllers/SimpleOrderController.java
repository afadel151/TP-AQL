package com.emp.controllers;

import com.emp.shared.models.Order;
import com.emp.services.SimpleOrderService;

public class SimpleOrderController {
    private final SimpleOrderService orderService;

    public SimpleOrderController(SimpleOrderService orderService) {
        this.orderService = orderService;
    }

    public Order createOrder(Order order) {
        return orderService.createOrder(order);
    }
}
