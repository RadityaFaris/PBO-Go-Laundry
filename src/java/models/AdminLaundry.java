package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLaundry extends Model<AdminLaundry> {

    private String id;
    private String nama;
    private String email;
    private String adminId;

    public AdminLaundry() {
        this.table = "admin_laundry";
        this.primaryKey = "adminId";
    }

    public AdminLaundry(String id, String nama, String email, String adminId) {
        this.table = "admin_laundry";
        this.primaryKey = "adminId";
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.adminId = adminId;
    }

    @Override
    public AdminLaundry toModel(ResultSet rs) {
        try {
            return new AdminLaundry(
                rs.getString("id"),
                rs.getString("nama"),
                rs.getString("email"),
                rs.getString("adminId")
            );
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAdminId() { return adminId; }
    public void setAdminId(String adminId) { this.adminId = adminId; }
}
