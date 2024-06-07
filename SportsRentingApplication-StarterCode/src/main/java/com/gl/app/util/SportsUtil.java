package com.gl.app.util;

import java.sql.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SportsUtil {
    private static final String URL = "jdbc:postgresql://localhost:5432/iap_noida?currentSchema=test_assesment";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";
    static AtomicInteger counter = new AtomicInteger();

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
    public int generateUniqueId(String columnName, String tableName, int initialValue) throws SQLException, ClassNotFoundException {
        int id=initialValue;
        Connection connection=SportsUtil.getConnection();

        String query="select Count("+columnName+") from "+tableName;

        PreparedStatement statement= connection.prepareStatement(query);

        ResultSet resultSet=statement.executeQuery();

        if(resultSet.next())
        {
            id=resultSet.getInt(1)+1;
            id+=counter.getAndIncrement();
        }
        return id;
    }


}
