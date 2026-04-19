package com.emp.repositories;

import com.emp.config.AbstractTestContainer;
import com.emp.shared.interfaces.OrderRepository;
import com.emp.shared.interfaces.ProductRepository;
import com.emp.shared.interfaces.UserRepository;
import com.emp.shared.models.Order;
import com.emp.shared.models.Product;
import com.emp.shared.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest extends AbstractTestContainer {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    public void cleanup() {
        orderRepository.deleteAll();
        productRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testSaveAndFindByUserId() {
        User user = User.builder().username("order_user").email("ou@mail.com").password("pass").role("USER").build();
        user = userRepository.save(user);

        Product product = Product.builder().name("Laptop").price(1000.0).stock(10).build();
        product = productRepository.save(product);

        Order order = Order.builder()
                .user(user)
                .product(product)
                .quantity(1)
                .totalPrice(1000.0)
                .orderDate(LocalDateTime.now())
                .status("PENDING")
                .build();
        order = orderRepository.save(order);

        List<Order> orders = orderRepository.findByUserId(user.getId());
        assertFalse(orders.isEmpty());
        assertEquals(1, orders.size());
        assertEquals("PENDING", orders.get(0).getStatus());
    }
}
