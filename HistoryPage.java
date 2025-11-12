import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class HistoryPage extends JFrame {

    public HistoryPage(String username) {
        setTitle("Booking History");
        setSize(700, 400); // slightly wider for name column
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Font font = new Font("Segoe UI", Font.PLAIN, 14);
        JLabel title = new JLabel("Booking History for " + username, SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        // ✅ Updated column headers to include Name
        String[] columns = {"Name", "Phone", "From", "To", "Date", "Budget", "Mode", "Hotel", "Food"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:travelapp.db")) {
            // ✅ Updated query to include name
            String query = "SELECT name, phone, fromCity, toCity, travelDate, budget, travelMode, selectedHotel, selectedFoodPlan FROM bookings WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                // ✅ Include name in row data
                Object[] row = {
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("fromCity"),
                    rs.getString("toCity"),
                    rs.getString("travelDate"),
                    rs.getDouble("budget"),
                    rs.getString("travelMode"),
                    rs.getString("selectedHotel"),
                    rs.getString("selectedFoodPlan")
                };
                tableModel.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading history!", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        setVisible(true);
    }
}