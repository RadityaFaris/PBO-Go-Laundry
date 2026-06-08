package db;

import model.ServiceLaundry;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceLaundryJDBC {

    // INSERT service baru
    public static boolean insert(ServiceLaundry s) {
        String sql = "INSERT INTO service_laundry (id, jenis_layanan, harga_per_kg, estimasi_waktu, is_available) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getId());
            ps.setString(2, s.getJenisLayanan());
            ps.setDouble(3, s.getHargaPerKg());
            ps.setInt(4, s.getEstimasiWaktu());
            ps.setBoolean(5, s.isAvailable());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error insert service: " + e.getMessage());
            return false;
        }
    }

    // CARI service berdasarkan ID
    public static ServiceLaundry findById(String id) {
        String sql = "SELECT * FROM service_laundry WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);

        } catch (SQLException e) {
            System.out.println("Error findById service: " + e.getMessage());
        }
        return null;
    }

    // AMBIL semua service
    public static List<ServiceLaundry> findAll() {
        List<ServiceLaundry> list = new ArrayList<>();
        String sql = "SELECT * FROM service_laundry";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) list.add(mapRow(rs));

        } catch (SQLException e) {
            System.out.println("Error findAll service: " + e.getMessage());
        }
        return list;
    }

    // AMBIL service yang tersedia saja
    public static List<ServiceLaundry> findAvailable() {
        List<ServiceLaundry> list = new ArrayList<>();
        String sql = "SELECT * FROM service_laundry WHERE is_available = TRUE";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) list.add(mapRow(rs));

        } catch (SQLException e) {
            System.out.println("Error findAvailable: " + e.getMessage());
        }
        return list;
    }

    // UPDATE service
    public static boolean update(ServiceLaundry s) {
        String sql = "UPDATE service_laundry SET jenis_layanan=?, harga_per_kg=?, estimasi_waktu=?, is_available=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getJenisLayanan());
            ps.setDouble(2, s.getHargaPerKg());
            ps.setInt(3, s.getEstimasiWaktu());
            ps.setBoolean(4, s.isAvailable());
            ps.setString(5, s.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error update service: " + e.getMessage());
            return false;
        }
    }

    // DELETE service
    public static boolean delete(String id) {
        String sql = "DELETE FROM service_laundry WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error delete service: " + e.getMessage());
            return false;
        }
    }

    private static ServiceLaundry mapRow(ResultSet rs) throws SQLException {
        return new ServiceLaundry(
            rs.getString("id"),
            rs.getString("jenis_layanan"),
            rs.getDouble("harga_per_kg"),
            rs.getInt("estimasi_waktu"),
            rs.getBoolean("is_available")
        );
    }
}
