package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class StatusHistory extends Model<StatusHistory> {

    private String statusID;
    private String status;
    private Date waktuUpdate;

    public StatusHistory() {
        this.table = "status_history";
        this.primaryKey = "statusID";
    }

    public StatusHistory(String statusID, String status) {
        this.table = "status_history";
        this.primaryKey = "statusID";
        this.statusID = statusID;
        this.status = status;
        this.waktuUpdate = new Date();
    }

    public StatusHistory(String statusID, String status, Date waktuUpdate) {
        this.table = "status_history";
        this.primaryKey = "statusID";
        this.statusID = statusID;
        this.status = status;
        this.waktuUpdate = waktuUpdate;
    }

    @Override
    public StatusHistory toModel(ResultSet rs) {
        try {
            return new StatusHistory(
                rs.getString("statusID"),
                rs.getString("status"),
                rs.getDate("waktuUpdate")
            );
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public void tampilkanStatus() {
        System.out.println("Status [" + statusID + "]: " + status + " | Waktu: " + waktuUpdate);
    }

    public void tampilNotifikasi() {
        System.out.println("Notifikasi Status: Order telah berubah menjadi " + status);
    }

    public String setKeterangan() {
        return "Status: " + status + " pada " + waktuUpdate;
    }

    public String getHistoryID() { return statusID; }
    public void setHistoryID(String statusID) { this.statusID = statusID; }

    public Date getWaktu() { return waktuUpdate; }
    public void setWaktu(Date waktuUpdate) { this.waktuUpdate = waktuUpdate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
