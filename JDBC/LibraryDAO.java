import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LibraryDAO {
    // Method to create the books table
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS books (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY, " +
                     "title VARCHAR(255) NOT NULL, " +
                     "author VARCHAR(255) NOT NULL)";
        try (Connection conn = MySqlConnector.mysqlConnection(null);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create a new book record
    public void createBook(String title, String author) {
        String sql = "INSERT INTO books (title, author) VALUES (?, ?)";
        try (Connection conn = MySqlConnector.mysqlConnection(null);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read book records
    public void readBooks() {
        String sql = "SELECT * FROM books";
        try (Connection conn = MySqlConnector.mysqlConnection(null);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Title: " + rs.getString("title") + ", Author: " + rs.getString("author"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update a book record
    public void updateBook(int id, String title, String author) {
        String sql = "UPDATE books SET title = ?, author = ? WHERE id = ?";
        try (Connection conn = MySqlConnector.mysqlConnection(null);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("Book updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a book record
    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = MySqlConnector.mysqlConnection(null);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Book deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LibraryDAO dao = new LibraryDAO();

        // Create the table
        dao.createTable();

        // Example usage
        dao.createBook("To Kill a Mockingbird", "Harper Lee");
        dao.readBooks();
        dao.updateBook(1, "1984", "George Orwell");
        dao.readBooks();
        dao.deleteBook(1);
        dao.readBooks();
    }
}
