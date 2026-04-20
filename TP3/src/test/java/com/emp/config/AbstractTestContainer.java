package com.emp.config;

import org.testcontainers.containers.MySQLContainer;

public abstract class AbstractTestContainer {
    static final MySQLContainer<?> mysqlContainer;

    static {
        mysqlContainer = new MySQLContainer<>("mysql:8.0")
                .withDatabaseName("testdb")
                .withUsername("testuser")
                .withPassword("testpass");
        mysqlContainer.start();
    }
}