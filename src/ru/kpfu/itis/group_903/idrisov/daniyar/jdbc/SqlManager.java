package ru.kpfu.itis.group_903.idrisov.daniyar.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlManager {

    private static final String URL = "jdbc:postgresql://localhost:5432/my_first_database";
    private static final String USER = "postgres";
    private static final String PASSWORD = "...";

    public static void main(String[] args) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.openConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from student");
        while (resultSet.next()) {
            System.out.println("ID " + resultSet.getInt("id"));
            System.out.println("First Name " + resultSet.getString("first_name"));
            System.out.println("Last Name " + resultSet.getString("last_name"));
            System.out.println("Age " + resultSet.getInt("age"));
            System.out.println("Group Number " + resultSet.getInt("group_number"));
        }
        System.out.println("-------------------");
        resultSet.close();
        resultSet = statement.executeQuery("select s.id as s_id, *\n" +
                "from student s\n" +
                "         full outer join mentor m on s.id = m." +
                "student_id;");
        while (resultSet.next()) {
            System.out.println("ID " + resultSet.getInt("s_id"));
        }
        connection.close();
    }

}

