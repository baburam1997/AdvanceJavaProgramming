import java.sql.*;

public class ScrollableResultSet {

    public static void main(String[] args) {
        String query = "SELECT * FROM books";

        try (Connection conn = MySqlConnector.mysqlConnection(null);
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = stmt.executeQuery(query)) {

            // Move cursor to the first row
            if (rs.first()) {
                System.out.println("First row: " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }

            // Move cursor to the last row
            if (rs.last()) {
                System.out.println("Last row: " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }

            // Move cursor to a specific row (e.g., 3rd row)
            if (rs.absolute(3)) {
                System.out.println("3rd row: " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }

            // Move cursor relative to its current position (e.g., move 2 rows forward)
            if (rs.relative(2)) {
                System.out.println("Moved 2 rows forward: " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }

            // Move cursor to the previous row
            if (rs.previous()) {
                System.out.println("Previous row: " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
