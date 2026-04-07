package com.emp.services;

// import com.emp.models.User;
// import com.emp;
import com.emp.repositories.UserRepository;

public class UserService {
     private UserRepository _userRepository;
    public UserService(UserRepository userRepository)
    {
        _userRepository = userRepository;
    }

    // public User getUserById(long id)
    // {
    //     return _userRepository.findUserById(id);
    // }
}
