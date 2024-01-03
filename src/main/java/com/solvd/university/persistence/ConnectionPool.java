package com.solvd.university.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConnectionPool {
    private final int connectionPoolSize;
    private final Queue<Connection> connectionQueue = new ConcurrentLinkedQueue<>();
    private static volatile ConnectionPool instance;
    private static final int NUMBER_OF_CONNECTIONS = 1;

    private ConnectionPool(int connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
        Properties property = new Properties();

        for (int i = 0; i < this.connectionPoolSize; i++) {
            Connection connection;
            try (FileInputStream fis = new FileInputStream("src/main/resources/jdbc.properties")) {
                property.load(fis);
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                        property.getProperty("URL"),
                        property.getProperty("USER_NAME"),
                        property.getProperty("PASSWORD")
                );
            } catch (IOException e) {
                throw new RuntimeException("Невозможно прочитать property файл!", e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Проблема с JDBC драйвером!", e);
            } catch (SQLException e) {
                throw new RuntimeException("Не удалось подключиться к базе данных!", e);
            }

            connectionQueue.add(connection);
        }

    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool(NUMBER_OF_CONNECTIONS);
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        while (connectionQueue.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return connectionQueue.remove();
    }

    public synchronized void releaseConnection(Connection connection) {
        if (connectionQueue.size() < this.connectionPoolSize) {
            connectionQueue.add(connection);
        }
        notify();
    }

}
