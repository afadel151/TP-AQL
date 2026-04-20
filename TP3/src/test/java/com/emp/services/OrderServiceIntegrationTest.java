package com.emp.services;

import com.emp.config.AbstractTestContainer;
import com.emp.shared.models.Order;
import com.emp.shared.models.Product;
import com.emp.shared.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class OrderServiceIntegrationTest extends AbstractTestContainer {

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
         if (dockerAvailable && mysqlContainer != null) {

            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
            registry.add("spring.datasource.username", mysqlContainer::getUsername);
            registry.add("spring.datasource.password", mysqlContainer::getPassword);
            registry.add("spring.datasource.driver-class-name", mysqlContainer::getDriverClassName);
            registry.add("spring.jpa.properties.hibernate.dialect",
                    () -> "org.hibernate.dialect.MySQLDialect");
        } else {
            registry.add("spring.datasource.url",
            () -> "jdbc:h2:mem:testdb;MODE=MySQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
            registry.add("spring.datasource.username", () -> "sa");
            registry.add("spring.datasource.password", () -> "");
            registry.add("spring.datasource.driver-class-name", () -> "org.h2.Driver");
            registry.add("spring.jpa.properties.hibernate.dialect",
                    () -> "org.hibernate.dialect.H2Dialect");
        }
    }

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
