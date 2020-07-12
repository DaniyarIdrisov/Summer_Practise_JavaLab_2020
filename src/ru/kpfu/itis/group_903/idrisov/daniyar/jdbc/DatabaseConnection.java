package ru.kpfu.itis.group_903.idrisov.daniyar.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public Connection openConnection(String url, String user, String password) {
        try {
            return DriverManager.getConnection(url,  user, password);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
