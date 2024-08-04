import java.sql.*;

public class DatabaseUtil {

    // Function to print metadata of a result set
    public void printResultSetMetaData(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        System.out.println("Column Count: " + columnCount);
        for (int i = 1; i <= columnCount; i++) {
            System.out.println("Column " + i + ": " + rsmd.getColumnName(i) + " of type " + rsmd.getColumnTypeName(i));
        }
    }

    // Function to print all data in the result set
    public void printResultSetData(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rs.getString(i) + " ");
            }
            System.out.println();
        }
    }

    // Function to demonstrate forward-only result set
    // Definition:
    // A forward-only result set is the simplest type of result set. It allows you to move only in one direction, from the first row to the last row. 
    // Once you move past a row, you cannot go back to it.

    // Use Case:
    // This type of result set is useful when you need to process rows sequentially, such as when generating a report or reading data for batch processing.
    public void fetchBooksForwardOnly() {
        String query = "SELECT * FROM books";
        try (Connection conn = MySqlConnector.mysqlConnection(null);
             Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Forward-only Result Set:");
            printResultSetMetaData(rs);
            printResultSetData(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to demonstrate scroll-insensitive result set
    // Definition:
    // A scroll-insensitive result set allows you to move both forward and backward, and to jump to specific rows within the result set. 
    // However, it does not reflect changes made to the database after the result set was created. The data remains consistent as of the time the query was executed.

    // Use Case:
    // This type of result set is useful when you need to browse through data non-sequentially (e.g., in a graphical user interface) 
    // without worrying about changes in the database affecting the data you have retrieved.
    public void fetchBooksScrollInsensitive() {
        String query = "SELECT * FROM books";
        try (Connection conn = MySqlConnector.mysqlConnection(null);
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Scroll-insensitive Result Set:");
            printResultSetMetaData(rs);
            printResultSetData(rs);

            // Demonstrating scrolling
            if (rs.first()) {
                System.out.println("First row: " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
            if (rs.last()) {
                System.out.println("Last row: " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to demonstrate scroll-sensitive result set
    // Definition:
    // A scroll-sensitive result set allows you to move both forward and backward, and to jump to specific rows within the result set. 
    // Unlike the scroll-insensitive result set, it reflects changes made to the database after the result set was created. If a row in the result set is updated, 
    // deleted, or inserted by another transaction, those changes are visible in the result set.

    // Use Case:
    // This type of result set is useful when you need real-time access to the data and need to see updates made by other transactions 
    // while navigating through the result set.
    public void fetchBooksScrollSensitive() {
        String query = "SELECT * FROM books";
        try (Connection conn = MySqlConnector.mysqlConnection(null);
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Scroll-sensitive Result Set:");
            printResultSetMetaData(rs);
            printResultSetData(rs);

            // Demonstrating scrolling
            if (rs.first()) {
                System.out.println("First row: " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
            if (rs.last()) {
                System.out.println("Last row: " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DatabaseUtil util = new DatabaseUtil();

        // Example usage
        util.fetchBooksForwardOnly();
        util.fetchBooksScrollInsensitive();
        util.fetchBooksScrollSensitive();
    }
}
