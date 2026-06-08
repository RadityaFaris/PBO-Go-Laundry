/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class Kurir extends Akun{
    private String kurirId;

    public Kurir(String id, String nama, String email, String kurirId) {
        super(id, nama, email);
        this.kurirId = kurirId;
    }

    public String getKurirId() { 
        return kurirId; 
    }
    
    public void setKurirId(String kurirId) { 
        this.kurirId = kurirId; 
    }
}
