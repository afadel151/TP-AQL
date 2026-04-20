package com.emp.services;

import com.emp.config.AbstractTestContainer;
import com.emp.shared.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserServiceIntegrationTest extends AbstractTestContainer {

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
    private UserService userService;

    @Autowired
    private com.emp.shared.interfaces.UserRepository userRepository;

    @AfterEach
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void testCreateAndFindUser() {
        User user = User.builder()
                .username("svc_user")
                .email("svc@mail.com")
                .password("secret")
                .role("USER")
                .build();

        User created = userService.createUser(user);
        assertNotNull(created.getId());

        Optional<User> found = userService.findByUsername("svc_user");
        assertTrue(found.isPresent());
        assertEquals("svc@mail.com", found.get().getEmail());
    }

    @Test
    public void testCreateDuplicateUsernameThrowsException() {
        User user1 = User.builder().username("dup_user").email("dup1@mail.com").password("sec").build();
        userService.createUser(user1);

        User user2 = User.builder().username("dup_user").email("dup2@mail.com").password("sec").build();
        assertThrows(IllegalArgumentException.class, () -> userService.createUser(user2));
    }
}
