package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

    private static final String URL = 
        "jdbc:sqlserver://localhost;" +
        "databaseName=OrganicFruitStore;" +
        "encrypt=true;" +
        "trustServerCertificate=true;";
    private static final String USER = "paula";
    private static final String PASSWORD = "paula123";

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Koneksi ke database berhasil!");
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Koneksi ditutup.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}