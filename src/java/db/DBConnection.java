package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL      = "jdbc:mysql://b9yehf0mgexd9o6intr0-mysql.services.clever-cloud.com:3306/b9yehf0mgexd9o6intr0?useSSL=true&allowPublicKeyRetrieval=true";
    private static final String USER     = "ufikktoh4fe70ebn";
    private static final String PASSWORD = "SgLoNwQ4yiXrMLPWODae";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver tidak ditemukan.", e);
        }
    }
}
