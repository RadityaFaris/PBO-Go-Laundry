package servlet;

import db.OrderJDBC;
import db.ServiceLaundryJDBC;
import model.Akun;
import model.Order;
import model.ServiceLaundry;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private final OrderJDBC          orderJDBC   = new OrderJDBC();
    private final ServiceLaundryJDBC serviceJDBC = new ServiceLaundryJDBC();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // Cek session login
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("akunLogin") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        Akun akun = (Akun) session.getAttribute("akunLogin");

        List<Order>          orders   = orderJDBC.findByUserId(akun.getId());
        List<ServiceLaundry> services = serviceJDBC.findAvailable();

        req.setAttribute("orders", orders);
        req.setAttribute("services", services);
        req.setAttribute("akun", akun);

        req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(req, res);
    }
}
