package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Akun extends Model<Akun> {

    private String id;
    private String nama;
    private String email;

    public Akun() {
        this.table = "akun";
        this.primaryKey = "id";
    }

    public Akun(String id, String nama, String email) {
        this.table = "akun";
        this.primaryKey = "id";
        this.id = id;
        this.nama = nama;
        this.email = email;
    }

    @Override
    public Akun toModel(ResultSet rs) {
        try {
            return new Akun(
                rs.getString("id"),
                rs.getString("nama"),
                rs.getString("email")
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
}
