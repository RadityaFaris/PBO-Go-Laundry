package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Kurir extends Model<Kurir> {

    private String id;
    private String nama;
    private String email;
    private String kurirId;

    public Kurir() {
        this.table = "kurir";
        this.primaryKey = "kurirId";
    }

    public Kurir(String id, String nama, String email, String kurirId) {
        this.table = "kurir";
        this.primaryKey = "kurirId";
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.kurirId = kurirId;
    }

    @Override
    public Kurir toModel(ResultSet rs) {
        try {
            return new Kurir(
                rs.getString("id"),
                rs.getString("nama"),
                rs.getString("email"),
                rs.getString("kurirId")
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

    public String getKurirId() { return kurirId; }
    public void setKurirId(String kurirId) { this.kurirId = kurirId; }
}
