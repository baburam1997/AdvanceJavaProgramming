import java.sql.Connection;
import java.sql.DriverManager;


public class MySqlConnector {
        // JDBC URL, username, and password of MySQL server
        private static final String JDBC_URL = "jdbc:mysql://localhost:3306/library_db";
        private static final String JDBC_USER = "babu1234";
        private static final String JDBC_PASSWORD = "babu1234";
    
        public static Connection mysqlConnection(String[] args) {
            try {
                // Load MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Establishing a connection
                Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                System.out.println("Connected to the MySQL database!");
                return connection;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    
