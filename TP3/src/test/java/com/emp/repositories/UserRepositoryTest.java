package com.emp.repositories;

import com.emp.config.AbstractTestContainer;
import com.emp.shared.interfaces.UserRepository;
import com.emp.shared.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserRepositoryTest extends AbstractTestContainer {

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
                    () -> "jdbc:h2:mem:testdb;MODE=MySQL;DB_CLOSE_DELAY=-1");
            registry.add("spring.datasource.username", () -> "sa");
            registry.add("spring.datasource.password", () -> "");
            registry.add("spring.datasource.driver-class-name", () -> "org.h2.Driver");
            registry.add("spring.jpa.properties.hibernate.dialect",
                    () -> "org.hibernate.dialect.H2Dialect");
        }
    }

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void testSaveAndFindByUsername() {
        User user = User.builder()
                .username("john_doe")
                .email("john@example.com")
                .password("secret")
                .role("ADMIN")
                .build();

        User savedUser = userRepository.save(user);
        assertNotNull(savedUser.getId());

        Optional<User> retrieved = userRepository.findByUsername("john_doe");
        assertTrue(retrieved.isPresent());
        assertEquals("john@example.com", retrieved.get().getEmail());
    }

    @Test
    public void testExistsByEmail() {
        User user = User.builder()
                .username("jane_doe")
                .email("jane@example.com")
                .password("secret")
                .role("USER")
                .build();
        userRepository.save(user);

        assertTrue(userRepository.existsByEmail("jane@example.com"));
        assertFalse(userRepository.existsByEmail("unknown@example.com"));
    }
}
