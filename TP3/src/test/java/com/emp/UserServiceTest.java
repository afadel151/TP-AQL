package com.emp;

import com.emp.shared.models.User;
import com.emp.shared.interfaces.UserRepositoryInterface;
import com.emp.repositories.UserRepository;
import com.emp.services.UserService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Test
    void getUserById_shouldCallFindUserByIdWithCorrectArgument() {
        // create the mock
        UserRepository mockRepository = Mockito.mock(UserRepository.class);

        // deifine behaviour
        User expectedUser = new User(1L, "Alice", "alice@example.com");
        when(mockRepository.findUserById(1L)).thenReturn(expectedUser);

        // inject 
        UserService userService = new UserService(mockRepository);

        User result = userService.getUserById(1L);
        // verif
        verify(mockRepository).findUserById(1L);
        assertEquals(expectedUser, result);
    }
}
