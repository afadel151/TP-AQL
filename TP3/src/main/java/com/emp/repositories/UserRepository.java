package com.emp.repositories;



import com.emp.shared.interfaces.UserRepositoryInterface;
import com.emp.shared.models.*;

public class UserRepository implements UserRepositoryInterface {
    // private final Map<Long, User> store = new HashMap<>();

    public UserRepository() {
        // store.put(1L, new User(1L, "Alice", "alice@example.com"));
        // store.put(2L, new User(2L, "Bob", "bob@example.com"));
    }

    @Override
    public User findUserById(long id) {
        // real DB logic goes here later
        return null;
    }

}
