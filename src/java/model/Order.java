package model;

import java.util.Date;

public class Order implements Notifikasi {
    private String orderId;
    private String userId;
    private Date tanggal;
    private String status;
    private double berat;
    private double totalHarga;
    private Payment pembayaran;
    private StatusHistory statusOrder;

    public Order(String orderId, String userId, Date tanggal,
                 String status, double berat, double totalHarga) {
        this.orderId = orderId;
        this.userId = userId;
        this.tanggal = tanggal;
        this.status = status;
        this.berat = berat;
        this.totalHarga = totalHarga;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public double getBerat() {
        return berat;
    }

    public void setBerat(double berat) {
        this.berat = berat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Sesuai diagram: gettotalHarga() huruf kecil
    public double gettotalHarga() {
        return totalHarga;
    }

    // Tetap sediakan versi standar Java juga
    public double getTotalHarga() {
        return totalHarga;
    }

    public Payment getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(Payment pembayaran) {
        this.pembayaran = pembayaran;
    }

    public StatusHistory getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusHistory statusOrder) {
        this.statusOrder = statusOrder;
    }

    public void createOrder() {
        this.status = "PENDING";
        this.tanggal = new Date();
        System.out.println("Order " + orderId + " berhasil dibuat.");
    }

    public void cancelOrder() {
        this.status = "CANCELLED";
        System.out.println("Order " + orderId + " dibatalkan.");
    }

    // Sesuai diagram: tanpa parameter
    public void calculatePrice() {
        if (pembayaran != null) {
            this.totalHarga = this.berat * pembayaran.getAmount();
        }
        System.out.println("Total harga dihitung: Rp" + totalHarga);
    }

    // Sesuai diagram: tanpa parameter
    public void updateStatus() {
        if (statusOrder != null) {
            this.status = statusOrder.getStatus();
        }
        System.out.println("Status order " + orderId + " diperbarui ke: " + status);
    }

    @Override
    public void tampilNotifikasi() {
        System.out.println("[ORDER] ID: " + orderId
            + " | Status: " + status
            + " | Total: Rp" + totalHarga);
    }
}
