package db;

import model.Payment;
import java.sql.*;

public class PaymentJDBC {

    // INSERT payment baru
    public static boolean insert(Payment p) {
        String sql = "INSERT INTO payment (id, amount, status) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getId());
            ps.setDouble(2, p.getAmount());
            ps.setString(3, p.getStatus());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error insert payment: " + e.getMessage());
            return false;
        }
    }

    // CARI payment berdasarkan ID
    public static Payment findById(String id) {
        String sql = "SELECT * FROM payment WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);

        } catch (SQLException e) {
            System.out.println("Error findById payment: " + e.getMessage());
        }
        return null;
    }

    // UPDATE status payment
    public static boolean updateStatus(String id, String status) {
        String sql = "UPDATE payment SET status = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setString(2, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updateStatus payment: " + e.getMessage());
            return false;
        }
    }

    private static Payment mapRow(ResultSet rs) throws SQLException {
        Payment p = new Payment(
            rs.getString("id"),
            rs.getDouble("amount")
        );
        p.setStatus(rs.getString("status"));
        return p;
    }
}
