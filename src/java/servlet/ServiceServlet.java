package servlet;

import db.ServiceLaundryJDBC;
import model.ServiceLaundry;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/service")
public class ServiceServlet extends HttpServlet {

    private final ServiceLaundryJDBC serviceJDBC = new ServiceLaundryJDBC();

    // GET: tampilkan semua service
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        List<ServiceLaundry> services = serviceJDBC.findAll();
        req.setAttribute("services", services);
        req.getRequestDispatcher("/WEB-INF/views/service.jsp").forward(req, res);
    }

    // POST: tambah service baru (khusus admin)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String id           = "SVC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        String jenisLayanan = req.getParameter("jenisLayanan");
        double hargaPerKg   = Double.parseDouble(req.getParameter("hargaPerKg"));
        int estimasiWaktu   = Integer.parseInt(req.getParameter("estimasiWaktu"));

        ServiceLaundry service = new ServiceLaundry(id, jenisLayanan, hargaPerKg, estimasiWaktu, true);
        serviceJDBC.insert(service);

        res.sendRedirect(req.getContextPath() + "/service");
    }
}
