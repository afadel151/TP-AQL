package com.emp.repositories;

import com.emp.config.AbstractTestContainer;
import com.emp.shared.interfaces.ProductRepository;
import com.emp.shared.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest extends AbstractTestContainer {

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
