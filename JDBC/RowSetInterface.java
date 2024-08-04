import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.WebRowSet;
import javax.sql.rowset.RowSetProvider;
import java.io.StringWriter;
import java.sql.*;

public class RowSetInterface {

    public static void main(String[] args) {
        String query = "SELECT * FROM books";

        // JdbcRowSet Example
        try (JdbcRowSet jdbcRowSet = RowSetProvider.newFactory().createJdbcRowSet()) {
            jdbcRowSet.setUrl("jdbc:mysql://localhost:3306/library_db");
            jdbcRowSet.setUsername("babu1234");
            jdbcRowSet.setPassword("babu1234");
            jdbcRowSet.setCommand(query);
            jdbcRowSet.execute();

            System.out.println("JdbcRowSet Example:");
            while (jdbcRowSet.first()) {
                System.out.println("Book: " + jdbcRowSet.getString("title") + " by " + jdbcRowSet.getString("author"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // CachedRowSet Example
        try (CachedRowSet cachedRowSet = RowSetProvider.newFactory().createCachedRowSet()) {
            cachedRowSet.setCommand(query);
            cachedRowSet.execute(MySqlConnector.mysqlConnection(null));

            System.out.println("\nCachedRowSet Example:");
            while (cachedRowSet.next()) {
                System.out.println("Book: " + cachedRowSet.getString("title") + " by " + cachedRowSet.getString("author"));
            }

            // Update a row
            cachedRowSet.absolute(2); // Move to the second row
            cachedRowSet.updateString("author", "Updated Author");
            cachedRowSet.updateRow();

            // Commit the changes
            cachedRowSet.acceptChanges(MySqlConnector.mysqlConnection(null));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // JoinRowSet Example
        try (JoinRowSet joinRowSet = RowSetProvider.newFactory().createJoinRowSet()) {
            // Assuming you have another table `authors` to join with
            joinRowSet.addRowSet(RowSetProvider.newFactory().createJdbcRowSet());
            joinRowSet.addRowSet(RowSetProvider.newFactory().createJdbcRowSet());
            
            // Assuming `books` table and `authors` table have a common key
            joinRowSet.setCommand("SELECT b.title, a.name FROM books b JOIN authors a ON b.author_id = a.id");
            joinRowSet.execute(MySqlConnector.mysqlConnection(null));

            System.out.println("\nJoinRowSet Example:");
            while (joinRowSet.next()) {
                System.out.println("Book: " + joinRowSet.getString("title") + " by " + joinRowSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // WebRowSet Example
        try (WebRowSet webRowSet = RowSetProvider.newFactory().createWebRowSet()) {
            webRowSet.setCommand(query);
            webRowSet.execute(MySqlConnector.mysqlConnection(null));

            System.out.println("\nWebRowSet Example (XML):");
            // Serialize the WebRowSet to XML
            StringWriter writer = new StringWriter();
            webRowSet.writeXml(writer);
            System.out.println(writer.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
