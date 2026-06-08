/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class Payment implements Notifikasi{
    private String id;
    private double amount;
    private String status;

    public Payment(String id, double amount) {
        this.id = id;
        this.amount = amount;
        this.status = "PENDING";
    }

    public String getId() { 
        return id; 
    }

    public double getAmount() { 
        return amount; 
    }

    public String getStatus() { 
        return status; 
    }
    public void setStatus(String status) { 
        this.status = status; 
    }

    public boolean isValid() {
        return amount > 0 && id != null && !id.isEmpty();
    }

    @Override
    public void tampilNotifikasi() {
        System.out.println("[PAYMENT] ID: " + id
            + " | Amount: Rp" + amount
            + " | Status: " + status);
    }
}
