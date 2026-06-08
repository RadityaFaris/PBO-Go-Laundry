/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class ServiceLaundry {
    private String id;
    private String jenisLayanan;
    private double hargaPerKg;
    private int estimasiWaktu;
    private boolean isAvailable;

    public ServiceLaundry(String id, String jenisLayanan, double hargaPerKg, int estimasiWaktu, boolean isAvailable) {
        this.id = id;
        this.jenisLayanan = jenisLayanan;
        this.hargaPerKg = hargaPerKg;
        this.estimasiWaktu = estimasiWaktu;
        this.isAvailable = isAvailable;
    }

    public String getId() { 
        return id; 
    }
    
    public void setId(String id) { 
        this.id = id; 
    }

    public String getJenisLayanan() { 
        return jenisLayanan; 
    }
    
    public void setJenisLayanan(String jenisLayanan) { 
        this.jenisLayanan = jenisLayanan; 
    }

    public double getHargaPerKg() { 
        return hargaPerKg; 
    }
    
    public void setHargaPerKg(double hargaPerKg) { 
        this.hargaPerKg = hargaPerKg; 
    }

    public int getEstimasiWaktu() { 
        return estimasiWaktu; 
    }
    public void setEstimasiWaktu(int estimasiWaktu) { 
        this.estimasiWaktu = estimasiWaktu; 
    }

    public boolean isAvailable() { 
        return isAvailable; 
    }
}
