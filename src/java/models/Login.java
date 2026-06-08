package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends Model<Login> {

    private String email;
    private String password;

    public Login() {
        this.table = "akun";
        this.primaryKey = "id";
    }

    public Login(String email, String password) {
        this.table = "akun";
        this.primaryKey = "id";
        this.email = email;
        this.password = password;
    }

    @Override
    public Login toModel(ResultSet rs) {
        try {
            Login login = new Login();
            login.setEmail(rs.getString("email"));
            login.setPassword(rs.getString("password"));
            return login;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Validasi format email dan panjang password sebelum dikirim ke DB.
     */
    public boolean validasiRegistrasi(String email, String password) {
        if (email == null || !email.contains("@")) return false;
        if (password == null || password.length() < 6) return false;
        return true;
    }

    /**
     * Cari akun di database berdasarkan email & password.
     * Mengembalikan Akun jika cocok, null jika tidak ditemukan.
     */
    public Akun validasiLogin(Akun akun) {
        this.where("email = '" + akun.getEmail() + "'");
        // Pastikan kolom password ada di tabel akun
        var results = this.get();
        if (!results.isEmpty()) {
            Login found = results.get(0);
            if (found.getPassword().equals(this.password)) {
                return akun;
            }
        }
        return null;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
