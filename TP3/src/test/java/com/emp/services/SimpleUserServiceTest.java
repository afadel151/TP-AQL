package com.emp.services;

import com.emp.interfaces.UserRepository;
import com.emp.shared.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SimpleUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Test
    public void testGetUserById() {
        // Arrange
        User expectedUser = User.builder()
                .id(1L)
                .username("testuser")
                .email("test@example.com")
                .password("password")
                .role("USER")
                .build();

        SimpleUserService userService = new SimpleUserService(userRepository);

        when(userRepository.findUserById(1L)).thenReturn(expectedUser);

        // Act
        User result = userService.getUserById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        assertEquals("test@example.com", result.getEmail());

        // Verify that findUserById was called with the correct argument
        verify(userRepository, times(1)).findUserById(1L);
    }

    @Test
    public void testGetUserByIdNotFound() {
        // Arrange
        SimpleUserService userService = new SimpleUserService(userRepository);

        when(userRepository.findUserById(999L)).thenReturn(null);

        // Act
        User result = userService.getUserById(999L);

        // Assert
        assertNull(result);

        // Verify that findUserById was called with the correct argument
        verify(userRepository, times(1)).findUserById(999L);
    }

    @Test
    public void testGetUserByIdThrowsException() {
        // Arrange
        SimpleUserService userService = new SimpleUserService(userRepository);

        when(userRepository.findUserById(1L)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            userService.getUserById(1L);
        });

        // Verify that findUserById was called with the correct argument
        verify(userRepository, times(1)).findUserById(1L);
    }
}
