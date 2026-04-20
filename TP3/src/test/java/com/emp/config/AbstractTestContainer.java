package com.emp.config;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

public abstract class AbstractTestContainer {
    public static final MySQLContainer<?> mysqlContainer;
    public static final boolean dockerAvailable;

    static {
        boolean isDockerAvailable = false;
        MySQLContainer<?> container = null;
        try {
            container = new MySQLContainer<>(
                    DockerImageName.parse("mysql:8.0").asCompatibleSubstituteFor("mysql"))
                    .withDatabaseName("testdb")
                    .withUsername("testuser")
                    .withPassword("testpass");
            container.start();
            isDockerAvailable = true;
            System.out.println("Docker available, using MySQL Testcontainer.");
        } catch (Exception e) {
            System.out.println("Docker/MySQL not available, falling back to H2. Error: " + e.getMessage());
            container = null;
        }
        dockerAvailable = isDockerAvailable;
        mysqlContainer = container;
    }
}