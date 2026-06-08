package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends Model<User> {

    private String id;
    private String nama;
    private String email;
    private String userId;

    public User() {
        this.table = "user";
        this.primaryKey = "userId";
    }

    public User(String id, String nama, String email, String userId) {
        this.table = "user";
        this.primaryKey = "userId";
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.userId = userId;
    }

    @Override
    public User toModel(ResultSet rs) {
        try {
            return new User(
                rs.getString("id"),
                rs.getString("nama"),
                rs.getString("email"),
                rs.getString("userId")
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

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
}
