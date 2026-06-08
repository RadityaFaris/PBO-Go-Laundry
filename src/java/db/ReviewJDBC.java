package db;

import model.Akun;
import model.Review;
import model.ServiceLaundry;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewJDBC {

    // INSERT review baru
    public static boolean insert(Review r) {
        String sql = "INSERT INTO review (id, user_id, service_id, rating, comment) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, r.getId());
            ps.setString(2, r.getUser().getId());
            ps.setString(3, r.getService().getId());
            ps.setInt(4, r.getRating());
            ps.setString(5, r.getComment());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error insert review: " + e.getMessage());
            return false;
        }
    }

    // CARI review berdasarkan service
    public static List<Review> findByServiceId(String serviceId) {
        List<Review> list = new ArrayList<>();
        String sql = "SELECT r.*, a.nama, a.email, s.jenis_layanan, s.harga_per_kg, s.estimasi_waktu, s.is_available "
                   + "FROM review r "
                   + "JOIN akun a ON r.user_id = a.id "
                   + "JOIN service_laundry s ON r.service_id = s.id "
                   + "WHERE r.service_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, serviceId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));

        } catch (SQLException e) {
            System.out.println("Error findByServiceId: " + e.getMessage());
        }
        return list;
    }

    private static Review mapRow(ResultSet rs) throws SQLException {
        Akun user = new Akun(
            rs.getString("user_id"),
            rs.getString("nama"),
            rs.getString("email")
        );
        ServiceLaundry service = new ServiceLaundry(
            rs.getString("service_id"),
            rs.getString("jenis_layanan"),
            rs.getDouble("harga_per_kg"),
            rs.getInt("estimasi_waktu"),
            rs.getBoolean("is_available")
        );
        return new Review(
            rs.getString("id"),
            user,
            service,
            rs.getInt("rating"),
            rs.getString("comment")
        );
    }
}
