import java.sql.*;

public class ResultSetHoldability {

    public static void main(String[] args) {
        String query = "SELECT * FROM books";

        try (Connection conn = MySqlConnector.mysqlConnection(null);
             Statement stmt = conn.createStatement(
                     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = stmt.executeQuery(query)) {

            // Check the holdability of the result set
            int holdability = stmt.getResultSetHoldability();
            if (holdability == ResultSet.HOLD_CURSORS_OVER_COMMIT) {
                System.out.println("The ResultSet is held open over commits.");
            } else if (holdability == ResultSet.CLOSE_CURSORS_AT_COMMIT) {
                System.out.println("The ResultSet is closed at commit.");
            }

            // Process the result set
            while (rs.next()) {
                System.out.println("Book: " + rs.getString("title") + " by " + rs.getString("author"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
