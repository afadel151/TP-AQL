package com.emp.controllers;

import com.emp.services.SimpleOrderService;
import com.emp.shared.dao.OrderDao;
import com.emp.shared.models.Order;
import com.emp.shared.models.Product;
import com.emp.shared.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SimpleOrderControllerTest {

    @Mock
    private SimpleOrderService orderService;

    @Mock
    private OrderDao orderDao;

    @Test
    public void testCreateOrder() {
        // Arrange
        User user = User.builder()
                .id(1L)
                .username("testuser")
                .email("test@example.com")
                .password("password")
                .role("USER")
                .build();

        Product product = Product.builder()
                .id(1L)
                .name("Test Product")
                .price(100.0)
                .stock(10)
                .build();

        Order inputOrder = Order.builder()
                .user(user)
                .product(product)
                .quantity(2)
                .totalPrice(200.0)
                .orderDate(LocalDateTime.now())
                .status("PENDING")
                .build();

        Order savedOrder = Order.builder()
                .id(1L)
                .user(user)
                .product(product)
                .quantity(2)
                .totalPrice(200.0)
                .orderDate(LocalDateTime.now())
                .status("PENDING")
                .build();

        SimpleOrderController orderController = new SimpleOrderController(orderService);

        when(orderService.createOrder(inputOrder)).thenReturn(savedOrder);

        // Act
        Order result = orderController.createOrder(inputOrder);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("PENDING", result.getStatus());

        // Verify that orderService.createOrder was called with the correct argument
        verify(orderService, times(1)).createOrder(inputOrder);
    }

    @Test
    public void testCreateOrderServiceThrowsException() {
        // Arrange
        User user = User.builder()
                .id(1L)
                .username("testuser")
                .email("test@example.com")
                .password("password")
                .role("USER")
                .build();

        Product product = Product.builder()
                .id(1L)
                .name("Test Product")
                .price(100.0)
                .stock(10)
                .build();

        Order inputOrder = Order.builder()
                .user(user)
                .product(product)
                .quantity(2)
                .totalPrice(200.0)
                .orderDate(LocalDateTime.now())
                .status("PENDING")
                .build();

        SimpleOrderController orderController = new SimpleOrderController(orderService);

        when(orderService.createOrder(inputOrder)).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            orderController.createOrder(inputOrder);
        });

        // Verify that orderService.createOrder was called with the correct argument
        verify(orderService, times(1)).createOrder(inputOrder);
    }
}
