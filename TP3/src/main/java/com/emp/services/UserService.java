package com.emp.services;

import com.emp.shared.interfaces.UserRepositoryInterface;
import com.emp.shared.models.User;
// import com.emp;
// import com.emp.repositories.UserRepository;

public class UserService {
     private final UserRepositoryInterface _userRepository;

    public UserService(UserRepositoryInterface userRepository)
    {
        this._userRepository = userRepository;
    }

    public User getUserById(long id)
    {
        return _userRepository.findUserById(id);
    }
}
