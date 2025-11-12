import java.sql.*;

public class DatabaseHelper {
    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:travelapp.db");
             Statement stmt = conn.createStatement()) {

            // Create users table
            String createUsersTable = """
                CREATE TABLE IF NOT EXISTS users (
                    username TEXT PRIMARY KEY,
                    password TEXT NOT NULL
                );
            """;
            stmt.execute(createUsersTable);

            // Create bookings table
            String createBookingsTable = """
                CREATE TABLE IF NOT EXISTS bookings (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT NOT NULL,
                    travel_mode TEXT,
                    phone TEXT,
                    from_city TEXT,
                    to_city TEXT,
                    travel_date TEXT,
                    budget REAL,
                    hotel TEXT,
                    food TEXT
                );
            """;
            stmt.execute(createBookingsTable);

        } catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }

    public static boolean userExists(String username, String password) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:travelapp.db");
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error checking user: " + e.getMessage());
            return false;
        }
    }

    public static void registerUser(String username, String password) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:travelapp.db");
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error registering user: " + e.getMessage());
        }
    }
}