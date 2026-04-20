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
            // Try to create and start a container to check if Docker is available
            container = new MySQLContainer<>(DockerImageName.parse("mysql:8.0").asCompatibleSubstituteFor("mysql"))
                    .withDatabaseName("testdb")
                    .withUsername("testuser")
                    .withPassword("testpass");
            container.start();
            isDockerAvailable = true;
        } catch (Exception e) {
            // Docker not available, will use H2
            System.out.println("Docker not available, falling back to H2 in-memory database. Error: " + e.getMessage());
            if (container != null) {
                try {
                    container.close();
                } catch (Exception ignored) {
                }
            }
        }
        dockerAvailable = isDockerAvailable;
        mysqlContainer = container;
    }
}