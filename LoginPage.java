import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginPage extends JFrame {

    public LoginPage() {
        setTitle("TravelApp : Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        Font font = new Font("Segoe UI", Font.PLAIN, 16);
        Color bg = new Color(245, 245, 245);
        Color blue = new Color(0, 120, 215);

        JLabel title = new JLabel("Login to TravelApp", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(blue);
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(bg);
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBackground(bg);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel userLabel = new JLabel("Username:", SwingConstants.RIGHT);
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Password:", SwingConstants.RIGHT);
        JPasswordField passField = new JPasswordField();

        formPanel.add(userLabel);
        formPanel.add(userField);
        formPanel.add(passLabel);
        formPanel.add(passField);
        add(formPanel, BorderLayout.CENTER);

        JButton loginBtn = new JButton("Login");
        loginBtn.setFont(font);
        loginBtn.setBackground(blue);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(bg);
        buttonPanel.add(loginBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        loginBtn.addActionListener(e -> {
            String username = userField.getText().trim();
            String password = new String(passField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter both username and password!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:travelapp.db")) {
                String createTable = "CREATE TABLE IF NOT EXISTS users (username TEXT PRIMARY KEY, password TEXT)";
                conn.createStatement().execute(createTable);

                // Check if user exists
                String checkUser = "SELECT * FROM users WHERE username = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkUser);
                checkStmt.setString(1, username);
                ResultSet checkRs = checkStmt.executeQuery();

                if (!checkRs.next()) {
                    // New user → insert and show message
                    String insertUser = "INSERT INTO users VALUES (?, ?)";
                    PreparedStatement insertStmt = conn.prepareStatement(insertUser);
                    insertStmt.setString(1, username);
                    insertStmt.setString(2, password);
                    insertStmt.executeUpdate();

                    JOptionPane.showMessageDialog(this, "New user registered!", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                    new TravelDetailsPage(new DataModel(username));
                    dispose();
                    return;
                }

                // Existing user → validate password
                String query = "SELECT * FROM users WHERE username = ? AND password = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    new TravelDetailsPage(new DataModel(username));
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}