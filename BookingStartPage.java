import javax.swing.*;
import java.awt.*;

public class BookingStartPage extends JFrame {
    public BookingStartPage() {
        setTitle("TravelApp : Start Booking");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        Font font = new Font("Segoe UI", Font.PLAIN, 16);
        Color bg = new Color(245, 245, 245);
        Color blue = new Color(0, 120, 215);

        JLabel title = new JLabel("Welcome to TravelApp", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(blue);
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(bg);
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBackground(bg);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel userLabel = new JLabel("Enter your name:", SwingConstants.RIGHT);
        userLabel.setFont(font);
        JTextField userField = new JTextField();
        userField.setFont(font);

        inputPanel.add(userLabel);
        inputPanel.add(userField);
        add(inputPanel, BorderLayout.CENTER);

        JButton nextBtn = new JButton("Start Booking");
        nextBtn.setFont(font);
        nextBtn.setBackground(blue);
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setFocusPainted(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(bg);
        buttonPanel.add(nextBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        nextBtn.addActionListener(e -> {
            String username = userField.getText().trim();
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your name!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            new TravelDetailsPage(new DataModel(username));
            dispose();
        });

        setVisible(true);
    }
}