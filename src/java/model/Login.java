package model;

public class Login {
    private Akun akun;
    private String email;
    private String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public boolean validasiRegistrasi(String email, String password) {
        if (email == null || !email.contains("@")) return false;
        if (password == null || password.length() < 6) return false;
        return true;
    }

    // Sesuai diagram: void (tidak return value)
    public void validasiLogin(Akun akun) {
        if (akun != null && akun.getEmail().equals(this.email)) {
            this.akun = akun;
            System.out.println("Login berhasil: " + akun.getNama());
        } else {
            System.out.println("Login gagal: email tidak cocok.");
        }
    }
}
