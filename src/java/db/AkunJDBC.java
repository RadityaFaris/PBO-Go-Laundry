package db;

import model.Akun;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AkunJDBC {

    // INSERT akun baru
    public static boolean insert(Akun akun, String password, String role) {
        String sql = "INSERT INTO akun (id, nama, email, password, role) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, akun.getId());
            ps.setString(2, akun.getNama());
            ps.setString(3, akun.getEmail());
            ps.setString(4, password);
            ps.setString(5, role);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error insert akun: " + e.getMessage());
            return false;
        }
    }

    // CARI akun berdasarkan ID
    public static Akun findById(String id) {
        String sql = "SELECT * FROM akun WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);

        } catch (SQLException e) {
            System.out.println("Error findById akun: " + e.getMessage());
        }
        return null;
    }

    // CARI akun berdasarkan email
    public static Akun findByEmail(String email) {
        String sql = "SELECT * FROM akun WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);

        } catch (SQLException e) {
            System.out.println("Error findByEmail: " + e.getMessage());
        }
        return null;
    }

    // AMBIL semua akun
    public static List<Akun> findAll() {
        List<Akun> list = new ArrayList<>();
        String sql = "SELECT * FROM akun";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) list.add(mapRow(rs));

        } catch (SQLException e) {
            System.out.println("Error findAll akun: " + e.getMessage());
        }
        return list;
    }

    // UPDATE akun
    public static boolean update(Akun akun) {
        String sql = "UPDATE akun SET nama = ?, email = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, akun.getNama());
            ps.setString(2, akun.getEmail());
            ps.setString(3, akun.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error update akun: " + e.getMessage());
            return false;
        }
    }

    // DELETE akun
    public static boolean delete(String id) {
        String sql = "DELETE FROM akun WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error delete akun: " + e.getMessage());
            return false;
        }
    }

    // LOGIN validasi email + password
    public static Akun login(String email, String password) {
        String sql = "SELECT * FROM akun WHERE email = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);

        } catch (SQLException e) {
            System.out.println("Error login: " + e.getMessage());
        }
        return null;
    }

    private static Akun mapRow(ResultSet rs) throws SQLException {
        return new Akun(
            rs.getString("id"),
            rs.getString("nama"),
            rs.getString("email")
        );
    }
}
