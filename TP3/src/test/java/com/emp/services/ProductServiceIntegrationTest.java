package com.emp.services;

import com.emp.config.AbstractTestContainer;
import com.emp.shared.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProductServiceIntegrationTest extends AbstractTestContainer {

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mysqlContainer::getUsername);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", mysqlContainer::getDriverClassName);
    }

    @Autowired
    private ProductService productService;

    @Autowired
    private com.emp.shared.interfaces.ProductRepository productRepository;

    @AfterEach
    public void cleanup() {
        productRepository.deleteAll();
    }

    @Test
    public void testCreateAndFindProduct() {
        Product p = Product.builder().name("Tablet").price(300.0).stock(15).build();
        Product saved = productService.createProduct(p);
        assertNotNull(saved.getId());

        List<Product> found = productService.findByName("Tablet");
        assertEquals(1, found.size());
    }

    @Test
    public void testCreateNegativePriceThrowsException() {
        Product p = Product.builder().name("BadProduct").price(-10.0).stock(5).build();
        assertThrows(IllegalArgumentException.class, () -> productService.createProduct(p));
    }
}
