package servlet;

import db.OrderJDBC;
import db.PaymentJDBC;
import db.ServiceLaundryJDBC;
import db.StatusHistoryJDBC;
import model.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private final OrderJDBC          orderJDBC   = new OrderJDBC();
    private final ServiceLaundryJDBC serviceJDBC = new ServiceLaundryJDBC();
    private final PaymentJDBC        paymentJDBC = new PaymentJDBC();
    private final StatusHistoryJDBC  statusJDBC  = new StatusHistoryJDBC();

    // GET: tampilkan form order atau detail order
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("akunLogin") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String action = req.getParameter("action");

        if ("detail".equals(action)) {
            String orderId = req.getParameter("id");
            Order order    = orderJDBC.findById(orderId);
            req.setAttribute("order", order);
            req.getRequestDispatcher("/WEB-INF/views/order-detail.jsp").forward(req, res);

        } else if ("cancel".equals(action)) {
            String orderId = req.getParameter("id");
            orderJDBC.updateStatus(orderId, "CANCELLED");
            res.sendRedirect(req.getContextPath() + "/dashboard");

        } else {
            List<ServiceLaundry> services = serviceJDBC.findAvailable();
            req.setAttribute("services", services);
            req.getRequestDispatcher("/WEB-INF/views/order-form.jsp").forward(req, res);
        }
    }

    // POST: buat order baru
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        Akun akun           = (Akun) session.getAttribute("akunLogin");

        String serviceId  = req.getParameter("serviceId");
        double berat      = Double.parseDouble(req.getParameter("berat"));

        // Hitung harga
        ServiceLaundry service = serviceJDBC.findById(serviceId);
        double totalHarga      = berat * service.getHargaPerKg();

        // Buat order
        String orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Order order    = new Order(orderId, akun.getId(), new Date(), "PENDING", berat, totalHarga);
        orderJDBC.insert(order, serviceId);

        // Buat payment
        String paymentId = "PAY-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Payment payment  = new Payment(paymentId, totalHarga);
        paymentJDBC.insert(payment);

        // Buat status history awal (Pending)
        String statusId      = "STS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        StatusHistory status = new Pending(statusId);
        statusJDBC.insert(status, orderId);

        res.sendRedirect(req.getContextPath() + "/dashboard?success=order");
    }
}
