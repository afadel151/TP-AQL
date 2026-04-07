package com.emp.repositories;

import java.util.HashMap;
import java.util.Map;

import com.emp.shared.models.*;
public class UserRepository {
    private final Map<Long, User> store = new HashMap<>();
     public UserRepository() {
        store.put(1L, new User(1L, "Alice", "alice@example.com"));
        store.put(2L, new User(2L, "Bob",   "bob@example.com"));
    }

    public User findUserById(long id) {
        return store.get(id);  
    }

}
