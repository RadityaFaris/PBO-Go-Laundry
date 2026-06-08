package db;

import model.StatusHistory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusHistoryJDBC {

    // INSERT status history baru
    public static boolean insert(StatusHistory sh, String orderId) {
        String sql = "INSERT INTO status_history (status_id, status, keterangan, order_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sh.getHistoryID());
            ps.setString(2, sh.getStatus());
            ps.setString(3, sh.setKeterangan());
            ps.setString(4, orderId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error insert status history: " + e.getMessage());
            return false;
        }
    }

    // CARI riwayat status berdasarkan order
    public static List<StatusHistory> findByOrderId(String orderId) {
        List<StatusHistory> list = new ArrayList<>();
        String sql = "SELECT * FROM status_history WHERE order_id = ? ORDER BY waktu_update ASC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));

        } catch (SQLException e) {
            System.out.println("Error findByOrderId: " + e.getMessage());
        }
        return list;
    }

    private static StatusHistory mapRow(ResultSet rs) throws SQLException {
        StatusHistory sh = new StatusHistory(
            rs.getString("status_id"),
            rs.getString("status")
        );
        sh.setWaktu(rs.getTimestamp("waktu_update"));
        return sh;
    }
}
