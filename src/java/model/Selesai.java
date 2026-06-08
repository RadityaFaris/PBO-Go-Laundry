/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class Selesai extends StatusHistory {
    public Selesai(String statusID) {
        super(statusID, "SELESAI");
    }

    @Override
    public void tampilkanStatus() {
        System.out.println("Laundry sudah selesai dan siap diambil.");
    }

    @Override
    public String setKeterangan() {
        return "Laundry telah selesai diproses.";
    }
}
