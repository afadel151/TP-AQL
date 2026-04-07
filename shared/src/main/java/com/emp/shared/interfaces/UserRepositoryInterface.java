package com.emp.shared.interfaces;

import com.emp.shared.models.User;

public interface UserRepositoryInterface {
    User findUserById(long id);
}
