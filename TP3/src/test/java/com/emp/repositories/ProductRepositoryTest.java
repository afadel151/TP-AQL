package com.emp.repositories;

import com.emp.config.AbstractTestContainer;
import com.emp.shared.interfaces.ProductRepository;
import com.emp.shared.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProductRepositoryTest extends AbstractTestContainer {

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        if (dockerAvailable) {
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
    private ProductRepository productRepository;

    @AfterEach
    public void cleanup() {
        productRepository.deleteAll();
    }

    @Test
    public void testSaveAndFindByName() {
        Product product = Product.builder()
                .name("Smartphone")
                .price(599.99)
                .stock(50)
                .build();

        Product savedProduct = productRepository.save(product);
        assertNotNull(savedProduct.getId());

        List<Product> retrieved = productRepository.findByNameContainingIgnoreCase("smart");
        assertFalse(retrieved.isEmpty());
        assertEquals("Smartphone", retrieved.get(0).getName());
    }

    @Test
    public void testFindByMaxPrice() {
        Product p1 = Product.builder().name("Keyboard").price(50.0).stock(100).build();
        Product p2 = Product.builder().name("Monitor").price(150.0).stock(20).build();
        
        productRepository.saveAll(List.of(p1, p2));

        List<Product> affordable = productRepository.findByPriceLessThanEqual(100.0);
        assertEquals(1, affordable.size());
        assertEquals("Keyboard", affordable.get(0).getName());
    }
}
