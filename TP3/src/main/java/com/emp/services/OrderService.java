package com.emp.services;

import com.emp.shared.exceptions.ResourceNotFoundException;
import com.emp.shared.models.Order;
import com.emp.shared.models.Product;
import com.emp.shared.models.User;
import com.emp.shared.interfaces.OrderRepository;
import com.emp.shared.interfaces.ProductRepository;
import com.emp.shared.interfaces.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Order createOrder(Long userId, Long productId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + productId));

        if (product.getStock() == null || product.getStock() < quantity) {
            throw new IllegalArgumentException("Insufficient stock for product: " + productId);
        }

        // Deduct stock
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);

        Order order = Order.builder()
                .user(user)
                .product(product)
                .quantity(quantity)
                .totalPrice(product.getPrice() * quantity)
                .orderDate(LocalDateTime.now())
                .status("PENDING")
                .build();

        return orderRepository.save(order);
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order confirmOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + orderId));
        order.setStatus("CONFIRMED");
        return orderRepository.save(order);
    }

    @Transactional
    public Order cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + orderId));

        if ("CONFIRMED".equals(order.getStatus())) {
            throw new IllegalStateException("Cannot cancel a confirmed order");
        }

        // Restore stock
        Product product = order.getProduct();
        product.setStock(product.getStock() + order.getQuantity());
        productRepository.save(product);

        order.setStatus("CANCELLED");
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Order not found: " + id);
        }
        orderRepository.deleteById(id);
    }
}