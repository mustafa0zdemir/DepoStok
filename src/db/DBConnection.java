package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5433/depo_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("DB bağlantısı başarılı");
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("DB bağlantısı başarısız", e);
        }
    }
}
