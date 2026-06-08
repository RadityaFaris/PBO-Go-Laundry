/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author USER
 */
public class StatusHistory implements Notifikasi{
    private String statusID;
    private String status;
    private Date waktuUpdate;

    public StatusHistory(String statusID, String status) {
        this.statusID = statusID;
        this.status = status;
        this.waktuUpdate = new Date();
    }

    public String getHistoryID() { 
        return statusID; 
    }
    
    public void setHistoryID(String statusID) { 
        this.statusID = statusID; 
    }

    public Date getWaktu() {
        return waktuUpdate; 
    }
    
    public void setWaktu(Date waktuUpdate) { 
        this.waktuUpdate = waktuUpdate; 
    }

    public String getStatus() { 
        return status; 
    }
    
    public void setStatus(String status) { 
        this.status = status; 
    }

    public void tampilkanStatus() {
        System.out.println("Status: " + status + " | Waktu: " + waktuUpdate);
    }

    public String setKeterangan() {
        return "Status diperbarui: " + status;
    }

    @Override
    public void tampilNotifikasi() {
        System.out.println("[NOTIF] " + setKeterangan() + " pada " + waktuUpdate);
    }
}
