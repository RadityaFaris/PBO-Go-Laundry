/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class AdminLaundry extends Akun{
    private String adminId;

    public AdminLaundry(String id, String nama, String email, String adminId) {
        super(id, nama, email);
        this.adminId = adminId;
    }

    public String getAdminId() { 
        return adminId; }
    
    public void setAdminId(String adminId) { 
        this.adminId = adminId; 
    }
}
