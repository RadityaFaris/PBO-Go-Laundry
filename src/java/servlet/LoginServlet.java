package servlet;

import db.AkunJDBC;
import model.Akun;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final AkunJDBC akunJDBC = new AkunJDBC();

    // GET: tampilkan halaman login
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, res);
    }

    // POST: proses login
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String email    = req.getParameter("email");
        String password = req.getParameter("password");

        Akun akun = akunJDBC.login(email, password);

        if (akun != null) {
            HttpSession session = req.getSession();
            session.setAttribute("akunLogin", akun);
            session.setAttribute("userId", akun.getId());
            session.setAttribute("namaUser", akun.getNama());

            res.sendRedirect(req.getContextPath() + "/dashboard");
        } else {
            req.setAttribute("error", "Email atau password salah.");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, res);
        }
    }
}
