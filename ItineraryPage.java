import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ItineraryPage extends JFrame {

    private DataModel booking;
    private JComboBox<String> hotelBox;
    private JComboBox<String> foodBox;
    private JTextField budgetField;

    public ItineraryPage(DataModel booking) {
        this.booking = booking;

        setTitle("TravelApp : Itinerary Planner");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        Font font = new Font("Segoe UI", Font.PLAIN, 16);
        Color bg = new Color(245, 245, 245);
        Color blue = new Color(0, 120, 215);

        JLabel title = new JLabel("Plan Your Itinerary", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(blue);
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(bg);
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBackground(bg);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));

        JLabel budgetLabel = new JLabel("Enter Total Budget (₹):", SwingConstants.RIGHT);
        budgetLabel.setFont(font);
        budgetField = new JTextField(String.valueOf(booking.getBudget()));
        budgetField.setFont(font);

        JButton suggestBtn = new JButton("Get Suggestions");
        suggestBtn.setFont(font);
        suggestBtn.setBackground(blue);
        suggestBtn.setForeground(Color.WHITE);
        suggestBtn.setFocusPainted(false);

        inputPanel.add(budgetLabel);
        inputPanel.add(budgetField);
        inputPanel.add(new JLabel());
        inputPanel.add(suggestBtn);

        add(inputPanel, BorderLayout.CENTER);

        JPanel optionsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        optionsPanel.setBackground(bg);
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        JLabel hotelLabel = new JLabel("Hotel Options:", SwingConstants.RIGHT);
        hotelLabel.setFont(font);
        hotelBox = new JComboBox<>();
        hotelBox.setFont(font);

        JLabel foodLabel = new JLabel("Food Plans:", SwingConstants.RIGHT);
        foodLabel.setFont(font);
        foodBox = new JComboBox<>();
        foodBox.setFont(font);

        optionsPanel.add(hotelLabel);
        optionsPanel.add(hotelBox);
        optionsPanel.add(foodLabel);
        optionsPanel.add(foodBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(bg);
        JButton nextBtn = new JButton("Next");
        nextBtn.setFont(font);
        nextBtn.setBackground(blue);
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setFocusPainted(false);
        buttonPanel.add(nextBtn);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(bg);
        southPanel.add(optionsPanel, BorderLayout.CENTER);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(southPanel, BorderLayout.SOUTH);

        suggestBtn.addActionListener(e -> suggestOptions());

        nextBtn.addActionListener(e -> {
            try {
                booking.setBudget(Double.parseDouble(budgetField.getText()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid budget!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            booking.setSelectedHotel((String) hotelBox.getSelectedItem());
            booking.setSelectedFoodPlan((String) foodBox.getSelectedItem());
            booking.saveBooking();

            dispose();
            new SummaryPage(booking).setVisible(true);
        });

        setVisible(true);
    }

    private void suggestOptions() {
        hotelBox.removeAllItems();
        foodBox.removeAllItems();

        try {
            double budget = Double.parseDouble(budgetField.getText());
            String city = booking.getToCity().trim();

            if (budget < 5000) {
                switch(city) {
                    case "Delhi":
                        hotelBox.addItem("Budget Inn Delhi");
                        hotelBox.addItem("Traveler's Hostel Delhi");
                        hotelBox.addItem("Metro Lodge Delhi");
                        hotelBox.addItem("City Stay Delhi");
                        hotelBox.addItem("Backpacker's Nest Delhi");
                        hotelBox.addItem("Heritage Budget Delhi");
                        foodBox.addItem("Delhi Chaat Corner");
                        foodBox.addItem("Street Tandoori Delhi");
                        foodBox.addItem("Budget Biryani Delhi");
                        foodBox.addItem("Quick Meals Delhi");
                        foodBox.addItem("Snack Hub Delhi");
                        foodBox.addItem("Local Thali Delhi");
                        break;
                    case "Mumbai":
                        hotelBox.addItem("Budget Inn Mumbai");
                        hotelBox.addItem("Traveler's Hostel Mumbai");
                        hotelBox.addItem("Metro Lodge Mumbai");
                        hotelBox.addItem("City Stay Mumbai");
                        hotelBox.addItem("Backpacker's Nest Mumbai");
                        hotelBox.addItem("Heritage Budget Mumbai");
                        foodBox.addItem("Mumbai Chaat Point");
                        foodBox.addItem("Street Tandoori Mumbai");
                        foodBox.addItem("Budget Biryani Mumbai");
                        foodBox.addItem("Quick Meals Mumbai");
                        foodBox.addItem("Snack Hub Mumbai");
                        foodBox.addItem("Local Thali Mumbai");
                        break;
                    case "Chennai":
                        hotelBox.addItem("Budget Inn Chennai");
                        hotelBox.addItem("Traveler's Hostel Chennai");
                        hotelBox.addItem("Metro Lodge Chennai");
                        hotelBox.addItem("City Stay Chennai");
                        hotelBox.addItem("Backpacker's Nest Chennai");
                        hotelBox.addItem("Heritage Budget Chennai");
                        foodBox.addItem("Local Snack Shack Chennai");
                        foodBox.addItem("Street Tandoori Chennai");
                        foodBox.addItem("Budget Biryani Chennai");
                        foodBox.addItem("Quick Meals Chennai");
                        foodBox.addItem("Snack Hub Chennai");
                        foodBox.addItem("Local Thali Chennai");
                        break;
                    case "Kolkata":
                        hotelBox.addItem("Budget Inn Kolkata");
                        hotelBox.addItem("Traveler's Hostel Kolkata");
                        hotelBox.addItem("Metro Lodge Kolkata");
                        hotelBox.addItem("City Stay Kolkata");
                        hotelBox.addItem("Backpacker's Nest Kolkata");
                        hotelBox.addItem("Heritage Budget Kolkata");
                        foodBox.addItem("Bengal Snack Shack");
                        foodBox.addItem("Street Tandoori Kolkata");
                        foodBox.addItem("Budget Biryani Kolkata");
                        foodBox.addItem("Quick Meals Kolkata");
                        foodBox.addItem("Snack Hub Kolkata");
                        foodBox.addItem("Local Thali Kolkata");
                        break;
                    case "Bangalore":
                        hotelBox.addItem("Budget Inn Bangalore");
                        hotelBox.addItem("Traveler's Hostel Bangalore");
                        hotelBox.addItem("Metro Lodge Bangalore");
                        hotelBox.addItem("City Stay Bangalore");
                        hotelBox.addItem("Backpacker's Nest Bangalore");
                        hotelBox.addItem("Heritage Budget Bangalore");
                        foodBox.addItem("Local Snack Shack Bangalore");
                        foodBox.addItem("Street Tandoori Bangalore");
                        foodBox.addItem("Budget Biryani Bangalore");
                        foodBox.addItem("Quick Meals Bangalore");
                        foodBox.addItem("Snack Hub Bangalore");
                        foodBox.addItem("Local Thali Bangalore");
                        break;
                }
            } else if (budget < 15000) {
                switch(city) {
                    case "Delhi":
                        hotelBox.addItem("Comfort Stay Delhi");
                        hotelBox.addItem("Urban Resort Delhi");
                        hotelBox.addItem("City View Inn Delhi");
                        hotelBox.addItem("Lotus Residency Delhi");
                        hotelBox.addItem("Parkside Suites Delhi");
                        hotelBox.addItem("Metro Comfort Delhi");
                        foodBox.addItem("City Bistro Delhi");
                        foodBox.addItem("The Spice Route Delhi");
                        foodBox.addItem("Midtown Meals Delhi");
                        foodBox.addItem("Fusion Feast Delhi");
                        foodBox.addItem("Heritage Thali Delhi");
                        foodBox.addItem("Urban Diner Delhi");
                        break;
                    case "Mumbai":
                        hotelBox.addItem("Comfort Stay Mumbai");
                        hotelBox.addItem("Urban Resort Mumbai");
                        hotelBox.addItem("City View Inn Mumbai");
                        hotelBox.addItem("Lotus Residency Mumbai");
                        hotelBox.addItem("Parkside Suites Mumbai");
                        hotelBox.addItem("Metro Comfort Mumbai");
                        foodBox.addItem("City Bistro Mumbai");
                        foodBox.addItem("The Spice Route Mumbai");
                        foodBox.addItem("Midtown Meals Mumbai");
                        foodBox.addItem("Fusion Feast Mumbai");
                        foodBox.addItem("Heritage Thali Mumbai");
                        foodBox.addItem("Urban Diner Mumbai");
                        break;
                    case "Chennai":
                        hotelBox.addItem("Comfort Stay Chennai");
                        hotelBox.addItem("Urban Resort Chennai");
                        hotelBox.addItem("City View Inn Chennai");
                        hotelBox.addItem("Lotus Residency Chennai");
                        hotelBox.addItem("Parkside Suites Chennai");
                        hotelBox.addItem("Metro Comfort Chennai");
                        foodBox.addItem("City Bistro Chennai");
                        foodBox.addItem("The Spice Route Chennai");
                        foodBox.addItem("Midtown Meals Chennai");
                        foodBox.addItem("Fusion Feast Chennai");
                        foodBox.addItem("Heritage Thali Chennai");
                        foodBox.addItem("Urban Diner Chennai");
                        break;
                }
            } else {
                switch(city) {
                    case "Delhi":
                        hotelBox.addItem("Grand Palace Delhi");
                        hotelBox.addItem("Skyline Suites Delhi");
                        hotelBox.addItem("Royal Orchid Delhi");
                        hotelBox.addItem("The Imperial Delhi");
                        hotelBox.addItem("Taj Gateway Delhi");
                        hotelBox.addItem("Elite Haven Delhi");
                        foodBox.addItem("Fine Dine Delhi");
                        foodBox.addItem("Le Gourmet Delhi");
                        foodBox.addItem("Chef's Table Delhi");
                        foodBox.addItem("Signature Tastes Delhi");
                        foodBox.addItem("Royal Feast Delhi");
                        foodBox.addItem("Global Palate Delhi");
                        break;
                    case "Mumbai":
                        hotelBox.addItem("Grand Palace Mumbai");
                        hotelBox.addItem("Skyline Suites Mumbai");
                        hotelBox.addItem("Royal Orchid Mumbai");
                        hotelBox.addItem("The Imperial Mumbai");
                        hotelBox.addItem("Taj Gateway Mumbai");
                        hotelBox.addItem("Elite Haven Mumbai");
                        foodBox.addItem("Fine Dine Mumbai");
                        foodBox.addItem("Le Gourmet Mumbai");
                        foodBox.addItem("Chef's Table Mumbai");
                        foodBox.addItem("Signature Tastes Mumbai");
                        foodBox.addItem("Royal Feast Mumbai");
                        foodBox.addItem("Global Palate Mumbai");
                        break;
                    case "Chennai":
                        hotelBox.addItem("Grand Palace Chennai");
                        hotelBox.addItem("Skyline Suites Chennai");
                        hotelBox.addItem("Royal Orchid Chennai");
                        hotelBox.addItem("The Imperial Chennai");
                        hotelBox.addItem("Taj Gateway Chennai");
                        hotelBox.addItem("Elite Haven Chennai");
                        foodBox.addItem("Fine Dine Chennai");
                        foodBox.addItem("Le Gourmet Chennai");
                        foodBox.addItem("Chef's Table Chennai");
                        foodBox.addItem("Signature Tastes Chennai");
                        foodBox.addItem("Royal Feast Chennai");
                        foodBox.addItem("Global Palate Chennai");
                        break;
                    case "Kolkata":
                        hotelBox.addItem("Grand Palace Kolkata");
                        hotelBox.addItem("Skyline Suites Kolkata");
                        hotelBox.addItem("Royal Orchid Kolkata");
                        hotelBox.addItem("The Imperial Kolkata");
                        hotelBox.addItem("Taj Gateway Kolkata");
                        hotelBox.addItem("Elite Haven Kolkata");
                        foodBox.addItem("Fine Dine Kolkata");
                        foodBox.addItem("Le Gourmet Kolkata");
                        foodBox.addItem("Chef's Table Kolkata");
                        foodBox.addItem("Signature Tastes Kolkata");
                        foodBox.addItem("Royal Feast Kolkata");
                        foodBox.addItem("Global Palate Kolkata");
                        break;
                    case "Bangalore":
                        hotelBox.addItem("Grand Palace Bangalore");
                        hotelBox.addItem("Skyline Suites Bangalore");
                        hotelBox.addItem("Royal Orchid Bangalore");
                        hotelBox.addItem("The Imperial Bangalore");
                        hotelBox.addItem("Taj Gateway Bangalore");
                        hotelBox.addItem("Elite Haven Bangalore");
                        foodBox.addItem("Fine Dine Bangalore");
                        foodBox.addItem("Le Gourmet Bangalore");
                        foodBox.addItem("Chef's Table Bangalore");
                        foodBox.addItem("Signature Tastes Bangalore");
                        foodBox.addItem("Royal Feast Bangalore");
                        foodBox.addItem("Global Palate Bangalore");
                        break;
                }
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid budget number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}