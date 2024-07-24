import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConnection {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String JDBC_USER = "babu1234";
    private static final String JDBC_PASSWORD = "babu1234";

    public static void main(String[] args) {
        try {
            // Add MySQL Connector/J JAR to the classpath dynamically
            File jarFile = new File("/usr/share/java/mysql-connector-java-9.0.0.jar");
            URL jarUrl = jarFile.toURI().toURL();
            
            // Get the system class loader
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            
            // Use reflection to access the private method addURL of URLClassLoader
            if (classLoader instanceof URLClassLoader) {
                URLClassLoader urlClassLoader = (URLClassLoader) classLoader;
                Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
                method.setAccessible(true);
                method.invoke(urlClassLoader, jarUrl);
            } else {
                // Handle the case where the class loader is not URLClassLoader
                System.out.println("System class loader is not an instance of URLClassLoader.");
            }

            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establishing a connection
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                System.out.println("Connected to the MySQL database!");

                // Creating a statement
                Statement statement = connection.createStatement();

                // Executing a simple query
                String query = "SELECT * FROM Users";
                ResultSet resultSet = statement.executeQuery(query);

                // Processing the result set
                while (resultSet.next()) {
                    int userId = resultSet.getInt("UserID");
                    String username = resultSet.getString("Username");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");

                    System.out.println("UserID: " + userId + ", Username: " + username + ", FirstName: " + firstName + ", LastName: " + lastName);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
