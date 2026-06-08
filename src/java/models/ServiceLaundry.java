package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceLaundry extends Model<ServiceLaundry> {

    private String id;
    private String jenisLayanan;
    private double hargaPerKg;
    private int estimasiWaktu;
    private boolean isAvailable;

    public ServiceLaundry() {
        this.table = "service_laundry";
        this.primaryKey = "id";
    }

    public ServiceLaundry(String id, String jenisLayanan, double hargaPerKg, int estimasiWaktu, boolean isAvailable) {
        this.table = "service_laundry";
        this.primaryKey = "id";
        this.id = id;
        this.jenisLayanan = jenisLayanan;
        this.hargaPerKg = hargaPerKg;
        this.estimasiWaktu = estimasiWaktu;
        this.isAvailable = isAvailable;
    }

    @Override
    public ServiceLaundry toModel(ResultSet rs) {
        try {
            return new ServiceLaundry(
                rs.getString("id"),
                rs.getString("jenisLayanan"),
                rs.getDouble("hargaPerKg"),
                rs.getInt("estimasiWaktu"),
                rs.getBoolean("isAvailable")
            );
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getJenisLayanan() { return jenisLayanan; }
    public void setJenisLayanan(String jenisLayanan) { this.jenisLayanan = jenisLayanan; }

    public double getHargaPerKg() { return hargaPerKg; }
    public void setHargaPerKg(double hargaPerKg) { this.hargaPerKg = hargaPerKg; }

    public int getEstimasiWaktu() { return estimasiWaktu; }
    public void setEstimasiWaktu(int estimasiWaktu) { this.estimasiWaktu = estimasiWaktu; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { this.isAvailable = available; }
}
