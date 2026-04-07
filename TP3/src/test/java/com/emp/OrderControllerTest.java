package com.emp;

import com.emp.controllers.OrderController;
import com.emp.services.OrderService;
import com.emp.shared.dao.OrderDao;
import com.emp.shared.models.Order;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class OrderControllerTest {

    @Test
    void createOrder_shouldCallOrderServiceAndOrderDao() {
        // mock
        OrderDao mockOrderDao = mock(OrderDao.class);
        OrderService mockOrderService = mock(OrderService.class);

        OrderController orderController = new OrderController(mockOrderService);
        Order order = new Order(1L, "Laptop", 2);

        orderController.createOrder(order);

        verify(mockOrderService).createOrder(order);
        // verify with real
        OrderService realOrderService = new OrderService(mockOrderDao);
        realOrderService.createOrder(order);
        verify(mockOrderDao).saveOrder(order);
    }
}