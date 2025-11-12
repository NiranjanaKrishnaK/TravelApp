import javax.swing.*;
import java.awt.*;

public class TravelDetailsPage extends JFrame {
    private DataModel model;

    public TravelDetailsPage(DataModel model) {
        this.model = model;

        setTitle("TravelApp : Travel Details");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        Font font = new Font("Segoe UI", Font.PLAIN, 16);
        Color bg = new Color(245, 245, 245);
        Color blue = new Color(0, 120, 215);

        JLabel title = new JLabel("Enter Travel Details", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(blue);
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(bg);
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10)); // updated row count
        formPanel.setBackground(bg);

        JTextField nameField = new JTextField(); // name field
        JTextField phoneField = new JTextField();

        String[] cities = {"Mumbai", "Kolkata", "Delhi", "Chennai", "Bangalore"};
        JComboBox<String> fromBox = new JComboBox<>(cities);
        JComboBox<String> toBox = new JComboBox<>(cities);
        JComboBox<String> modeBox = new JComboBox<>(new String[]{"Flight", "Train", "Bus"});

        String[] days = new String[31];
        for (int i = 0; i < 31; i++) days[i] = String.valueOf(i + 1);
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] years = {"2025", "2026", "2027"};

        JComboBox<String> dayBox = new JComboBox<>(days);
        JComboBox<String> monthBox = new JComboBox<>(months);
        JComboBox<String> yearBox = new JComboBox<>(years);

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.setBackground(bg);
        datePanel.add(dayBox);
        datePanel.add(monthBox);
        datePanel.add(yearBox);

        formPanel.add(new JLabel("Traveler Name:", SwingConstants.RIGHT));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Phone Number:", SwingConstants.RIGHT));
        formPanel.add(phoneField);
        formPanel.add(new JLabel("From City:", SwingConstants.RIGHT));
        formPanel.add(fromBox);
        formPanel.add(new JLabel("To City:", SwingConstants.RIGHT));
        formPanel.add(toBox);
        formPanel.add(new JLabel("Travel Date:", SwingConstants.RIGHT));
        formPanel.add(datePanel);
        formPanel.add(new JLabel("Travel Mode:", SwingConstants.RIGHT));
        formPanel.add(modeBox);

        add(formPanel, BorderLayout.CENTER);

        JButton nextBtn = new JButton("Next");
        nextBtn.setFont(font);
        nextBtn.setBackground(blue);
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setFocusPainted(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(bg);
        buttonPanel.add(nextBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        nextBtn.addActionListener(e -> {
            model.setName(nameField.getText().trim());
            model.setPhone(phoneField.getText().trim());
            model.setFromCity((String) fromBox.getSelectedItem());
            model.setToCity((String) toBox.getSelectedItem());
            model.setTravelDate(dayBox.getSelectedItem() + " " + monthBox.getSelectedItem() + " " + yearBox.getSelectedItem());
            model.setTravelMode((String) modeBox.getSelectedItem());

            new ItineraryPage(model);
            dispose();
        });

        setVisible(true);
    }
}