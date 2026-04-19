package com.emp.repositories;

import com.emp.config.AbstractTestContainer;
import com.emp.shared.interfaces.UserRepository;
import com.emp.shared.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserRepositoryTest extends AbstractTestContainer {

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
