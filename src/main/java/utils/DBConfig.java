package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
    public static final String dbUser = "postgres";
    public static final String password = "0706";
    static String url;
    static String host = "localhost";

    static String dbName = "WereHouse";
    static String port = "5432";

    // ulanish uchun alohida method :
    public static Connection connection() {
        Connection connection = null;
        url = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
        try {
            Class.forName("org.postgresql.Driver");


            connection = DriverManager.getConnection(url, dbUser, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
