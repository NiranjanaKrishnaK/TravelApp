import java.sql.*;
import javax.swing.*;

public class DataModel {
    private String username;
    private String name; // ✅ New field
    private String phone;
    private String fromCity;
    private String toCity;
    private String travelDate;
    private double budget;
    private String travelMode;
    private String selectedHotel;
    private String selectedFoodPlan;

    public DataModel(String username) {
        this.username = username;
    }

    public String getUsername() { return username; }

    // ✅ New getter and setter for name
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setPhone(String phone) { this.phone = phone; }
    public void setFromCity(String fromCity) { this.fromCity = fromCity; }
    public void setToCity(String toCity) { this.toCity = toCity; }
    public void setTravelDate(String travelDate) { this.travelDate = travelDate; }
    public void setBudget(double budget) { this.budget = budget; }
    public void setTravelMode(String travelMode) { this.travelMode = travelMode; }
    public void setSelectedHotel(String selectedHotel) { this.selectedHotel = selectedHotel; }
    public void setSelectedFoodPlan(String selectedFoodPlan) { this.selectedFoodPlan = selectedFoodPlan; }

    public String getPhone() { return phone; }
    public String getFromCity() { return fromCity; }
    public String getToCity() { return toCity; }
    public String getTravelDate() { return travelDate; }
    public double getBudget() { return budget; }
    public String getTravelMode() { return travelMode; }
    public String getSelectedHotel() { return selectedHotel; }
    public String getSelectedFoodPlan() { return selectedFoodPlan; }

    public void saveBooking() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:travelapp.db")) {
            String createTable = "CREATE TABLE IF NOT EXISTS bookings (" +
                    "username TEXT, name TEXT, phone TEXT, fromCity TEXT, toCity TEXT, travelDate TEXT, " +
                    "budget REAL, travelMode TEXT, selectedHotel TEXT, selectedFoodPlan TEXT)";
            Statement stmt = conn.createStatement();
            stmt.execute(createTable);

            String insert = "INSERT INTO bookings VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insert);
            pstmt.setString(1, username);
            pstmt.setString(2, name); // ✅ Save name
            pstmt.setString(3, phone);
            pstmt.setString(4, fromCity);
            pstmt.setString(5, toCity);
            pstmt.setString(6, travelDate);
            pstmt.setDouble(7, budget);
            pstmt.setString(8, travelMode);
            pstmt.setString(9, selectedHotel);
            pstmt.setString(10, selectedFoodPlan);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving booking to database!", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}