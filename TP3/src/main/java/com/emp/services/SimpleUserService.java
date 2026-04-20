package com.emp.services;

import com.emp.interfaces.UserRepository;
import com.emp.shared.models.User;

public class SimpleUserService {
    private final UserRepository userRepository;

    public SimpleUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(long id) {
        return userRepository.findUserById(id);
    }
}
