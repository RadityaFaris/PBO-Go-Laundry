package servlet;

import db.AkunJDBC;
import model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final AkunJDBC akunJDBC = new AkunJDBC();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String nama     = req.getParameter("nama");
        String email    = req.getParameter("email");
        String password = req.getParameter("password");

        // Cek email sudah terdaftar
        if (akunJDBC.findByEmail(email) != null) {
            req.setAttribute("error", "Email sudah terdaftar.");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, res);
            return;
        }

        // Buat user baru
        String id     = UUID.randomUUID().toString();
        String userId = "USR-" + id.substring(0, 8).toUpperCase();
        User user     = new User(id, nama, email, userId);

        boolean berhasil = akunJDBC.insert(user, password, "USER");

        if (berhasil) {
            res.sendRedirect(req.getContextPath() + "/login?success=1");
        } else {
            req.setAttribute("error", "Registrasi gagal, coba lagi.");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, res);
        }
    }
}
