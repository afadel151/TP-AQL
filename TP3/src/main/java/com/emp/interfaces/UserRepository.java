package com.emp.interfaces;

import com.emp.shared.models.User;

public interface UserRepository {
    User findUserById(long id);
}
