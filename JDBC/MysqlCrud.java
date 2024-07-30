import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlCrud {

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

                // Create the employee table
                createEmployeeTable(connection);

                // Insert data into the employee table
                insertEmployeeData(connection, 1, "John", "Doe", "Engineering", 50000);
                insertEmployeeData(connection, 2, "Jane", "Smith", "Marketing", 60000);

                // Modify data in the employee table
                updateEmployeeSalary(connection, 1, 55000);

                // Delete a row from the employee table
                deleteEmployee(connection, 2);

                // Display all employees
                displayEmployees(connection);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createEmployeeTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Employee (" +
                "EmployeeID INT PRIMARY KEY, " +
                "FirstName VARCHAR(50), " +
                "LastName VARCHAR(50), " +
                "Department VARCHAR(50), " +
                "Salary DECIMAL(10, 2))";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSQL);
            System.out.println("Employee table created.");
        }
    }

    private static void insertEmployeeData(Connection connection, int employeeID, String firstName, String lastName, String department, double salary) throws SQLException {
        String insertSQL = String.format("INSERT INTO Employee (EmployeeID, FirstName, LastName, Department, Salary) " +
                "VALUES (%d, '%s', '%s', '%s', %.2f)", employeeID, firstName, lastName, department, salary);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(insertSQL);
            System.out.println("Inserted data into Employee table.");
        }
    }

    private static void updateEmployeeSalary(Connection connection, int employeeID, double newSalary) throws SQLException {
        String updateSQL = String.format("UPDATE Employee SET Salary = %.2f WHERE EmployeeID = %d", newSalary, employeeID);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(updateSQL);
            System.out.println("Updated salary for EmployeeID: " + employeeID);
        }
    }

    private static void deleteEmployee(Connection connection, int employeeID) throws SQLException {
        String deleteSQL = String.format("DELETE FROM Employee WHERE EmployeeID = %d", employeeID);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(deleteSQL);
            System.out.println("Deleted EmployeeID: " + employeeID);
        }
    }

    private static void displayEmployees(Connection connection) throws SQLException {
        String query = "SELECT * FROM Employee";
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int employeeID = resultSet.getInt("EmployeeID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String department = resultSet.getString("Department");
                double salary = resultSet.getDouble("Salary");

                System.out.println("EmployeeID: " + employeeID + ", FirstName: " + firstName + ", LastName: " + lastName +
                        ", Department: " + department + ", Salary: " + salary);
            }
        }
    }
}

