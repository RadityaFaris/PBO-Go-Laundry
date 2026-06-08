/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class Proses extends StatusHistory {
    
    public Proses(String statusID) {
        super(statusID, "PROSES");
    }

    @Override
    public void tampilkanStatus() {
        System.out.println("Laundry sedang dalam proses pencucian.");
    }

    @Override
    public String setKeterangan() {
        return "Laundry sedang dikerjakan oleh tim kami.";
    }
}
