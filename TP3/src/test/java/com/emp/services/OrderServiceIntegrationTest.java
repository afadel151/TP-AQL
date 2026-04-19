package com.emp.services;

import com.emp.config.AbstractTestContainer;
import com.emp.shared.models.Order;
import com.emp.shared.models.Product;
import com.emp.shared.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class OrderServiceIntegrationTest extends AbstractTestContainer {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private com.emp.shared.interfaces.OrderRepository orderRepository;
    @Autowired
    private com.emp.shared.interfaces.ProductRepository productRepository;
    @Autowired
    private com.emp.shared.interfaces.UserRepository userRepository;

    @AfterEach
    public void cleanup() {
        orderRepository.deleteAll();
        productRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testCreateOrderDeductsStock() {
        User user = User.builder().username("os_user").email("os@mail.com").password("pass").role("USER").build();
        user = userService.createUser(user);

        Product product = Product.builder().name("Phone").price(500.0).stock(10).build();
        product = productService.createProduct(product);

        Order order = orderService.createOrder(user.getId(), product.getId(), 2);
        
        assertNotNull(order.getId());
        assertEquals("PENDING", order.getStatus());

        // Check stock is deducted
        Product updatedProduct = productService.findById(product.getId()).get();
        assertEquals(8, updatedProduct.getStock());
    }

    @Test
    public void testCancelOrderRestoresStock() {
        User user = User.builder().username("cancel_user").email("cu@mail.com").password("pass").build();
        user = userService.createUser(user);

        Product product = Product.builder().name("TV").price(800.0).stock(5).build();
        product = productService.createProduct(product);

        Order order = orderService.createOrder(user.getId(), product.getId(), 1);
        assertEquals(4, productService.findById(product.getId()).get().getStock());

        orderService.cancelOrder(order.getId());
        
        Order cancelledOrder = orderService.findById(order.getId()).get();
        assertEquals("CANCELLED", cancelledOrder.getStatus());

        // Stock restored
        Product restoredProduct = productService.findById(product.getId()).get();
        assertEquals(5, restoredProduct.getStock());
    }
}
