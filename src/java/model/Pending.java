/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class Pending extends StatusHistory {
    
    public Pending(String statusID) {
        super(statusID, "PENDING");
    }

    @Override
    public void tampilkanStatus() {
        System.out.println("Order sedang menunggu konfirmasi.");
    }

    @Override
    public String setKeterangan() {
        return "Order baru masuk, menunggu diproses oleh admin.";
    }
}
