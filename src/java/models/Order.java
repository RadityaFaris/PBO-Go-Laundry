package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Order extends Model<Order> {

    private String orderId;
    private String userId;
    private Date tanggal;
    private String status;
    private double berat;
    private double totalHarga;
    // Relasi ke Payment dan StatusHistory dikelola terpisah
    // (ambil via Payment model dan StatusHistory model menggunakan orderId)

    public Order() {
        this.table = "order";
        this.primaryKey = "orderId";
    }

    public Order(String orderId, String userId, Date tanggal, String status, double berat, double totalHarga) {
        this.table = "order";
        this.primaryKey = "orderId";
        this.orderId = orderId;
        this.userId = userId;
        this.tanggal = tanggal;
        this.status = status;
        this.berat = berat;
        this.totalHarga = totalHarga;
    }

    @Override
    public Order toModel(ResultSet rs) {
        try {
            return new Order(
                rs.getString("orderId"),
                rs.getString("userId"),
                rs.getDate("tanggal"),
                rs.getString("status"),
                rs.getDouble("berat"),
                rs.getDouble("totalHarga")
            );
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public void tampilNotifikasi() {
        System.out.println("Notifikasi Order [" + orderId + "]: Status = " + status);
    }

    public void createOrder() {
        this.status = "PENDING";
        this.insert();
    }

    public void cancelOrder() {
        this.status = "CANCELLED";
        this.update();
    }

    public double calculatePrice(double hargaPerKg) {
        this.totalHarga = this.berat * hargaPerKg;
        return this.totalHarga;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
        this.update();
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public Date getTanggal() { return tanggal; }
    public void setTanggal(Date tanggal) { this.tanggal = tanggal; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getBerat() { return berat; }
    public void setBerat(double berat) { this.berat = berat; }

    public double getTotalHarga() { return totalHarga; }
    public void setTotalHarga(double totalHarga) { this.totalHarga = totalHarga; }
}
