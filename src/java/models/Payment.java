package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Payment extends Model<Payment> {

    private String id;
    private double amount;
    private String status;

    public Payment() {
        this.table = "payment";
        this.primaryKey = "id";
    }

    public Payment(String id, double amount) {
        this.table = "payment";
        this.primaryKey = "id";
        this.id = id;
        this.amount = amount;
        this.status = "PENDING";
    }

    public Payment(String id, double amount, String status) {
        this.table = "payment";
        this.primaryKey = "id";
        this.id = id;
        this.amount = amount;
        this.status = status;
    }

    @Override
    public Payment toModel(ResultSet rs) {
        try {
            return new Payment(
                rs.getString("id"),
                rs.getDouble("amount"),
                rs.getString("status")
            );
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public void tampilNotifikasi() {
        System.out.println("Notifikasi Payment [" + id + "]: Status = " + status + ", Amount = " + amount);
    }

    public boolean isValid() {
        return id != null && !id.isEmpty() && amount > 0 && status != null;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
