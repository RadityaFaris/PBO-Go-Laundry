package db;

import model.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderJDBC {

    // INSERT order baru
    public static boolean insert(Order o, String serviceId) {
        String sql = "INSERT INTO `order` (order_id, user_id, service_id, tanggal, status, berat, total_harga) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, o.getOrderId());
            ps.setString(2, o.getUserId());
            ps.setString(3, serviceId);
            ps.setDate(4, new java.sql.Date(o.getTanggal().getTime()));
            ps.setString(5, o.getStatus());
            ps.setDouble(6, o.getBerat());
            ps.setDouble(7, o.getTotalHarga());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error insert order: " + e.getMessage());
            return false;
        }
    }

    // CARI order berdasarkan ID
    public static Order findById(String orderId) {
        String sql = "SELECT * FROM `order` WHERE order_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);

        } catch (SQLException e) {
            System.out.println("Error findById order: " + e.getMessage());
        }
        return null;
    }

    // CARI order berdasarkan user
    public static List<Order> findByUserId(String userId) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM `order` WHERE user_id = ? ORDER BY tanggal DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));

        } catch (SQLException e) {
            System.out.println("Error findByUserId: " + e.getMessage());
        }
        return list;
    }

    // AMBIL semua order
    public static List<Order> findAll() {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM `order` ORDER BY tanggal DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) list.add(mapRow(rs));

        } catch (SQLException e) {
            System.out.println("Error findAll order: " + e.getMessage());
        }
        return list;
    }

    // UPDATE status order
    public static boolean updateStatus(String orderId, String status) {
        String sql = "UPDATE `order` SET status = ? WHERE order_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setString(2, orderId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updateStatus order: " + e.getMessage());
            return false;
        }
    }

    private static Order mapRow(ResultSet rs) throws SQLException {
        return new Order(
            rs.getString("order_id"),
            rs.getString("user_id"),
            rs.getDate("tanggal"),
            rs.getString("status"),
            rs.getDouble("berat"),
            rs.getDouble("total_harga")
        );
    }
}
