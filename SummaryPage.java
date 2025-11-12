import javax.swing.*;
import java.awt.*;

public class SummaryPage extends JFrame {

    public SummaryPage(DataModel booking) {
        setTitle("Booking Summary");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        Font font = new Font("Segoe UI", Font.PLAIN, 16);
        Color bg = new Color(245, 245, 245);
        Color blue = new Color(0, 120, 215);

        JLabel title = new JLabel("Your Booking Summary", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(blue);
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(bg);
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);

        JPanel summaryPanel = new JPanel(new GridLayout(9, 2, 10, 10));
        summaryPanel.setBackground(bg);
        summaryPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        summaryPanel.add(new JLabel("Name:", SwingConstants.RIGHT));
        summaryPanel.add(new JLabel(booking.getName())); // ✅ updated to show full name
        summaryPanel.add(new JLabel("Phone:", SwingConstants.RIGHT));
        summaryPanel.add(new JLabel(booking.getPhone()));
        summaryPanel.add(new JLabel("From City:", SwingConstants.RIGHT));
        summaryPanel.add(new JLabel(booking.getFromCity()));
        summaryPanel.add(new JLabel("To City:", SwingConstants.RIGHT));
        summaryPanel.add(new JLabel(booking.getToCity()));
        summaryPanel.add(new JLabel("Travel Date:", SwingConstants.RIGHT));
        summaryPanel.add(new JLabel(booking.getTravelDate()));
        summaryPanel.add(new JLabel("Budget (₹):", SwingConstants.RIGHT));
        summaryPanel.add(new JLabel(String.valueOf(booking.getBudget())));
        summaryPanel.add(new JLabel("Travel Mode:", SwingConstants.RIGHT));
        summaryPanel.add(new JLabel(booking.getTravelMode()));
        summaryPanel.add(new JLabel("Hotel:", SwingConstants.RIGHT));
        summaryPanel.add(new JLabel(booking.getSelectedHotel()));
        summaryPanel.add(new JLabel("Food Plan:", SwingConstants.RIGHT));
        summaryPanel.add(new JLabel(booking.getSelectedFoodPlan()));

        add(summaryPanel, BorderLayout.CENTER);

        JButton closeBtn = new JButton("Close");
        closeBtn.setFont(font);
        closeBtn.setBackground(blue);
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setFocusPainted(false);

        JButton historyBtn = new JButton("View Booking History");
        historyBtn.setFont(font);
        historyBtn.setBackground(Color.GRAY);
        historyBtn.setForeground(Color.WHITE);
        historyBtn.setFocusPainted(false);

        historyBtn.addActionListener(e -> new HistoryPage(booking.getUsername()));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(bg);
        buttonPanel.add(closeBtn);
        buttonPanel.add(historyBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        closeBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Thank you for booking with TravelApp!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        setVisible(true);
    }
}