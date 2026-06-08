package servlet;

import db.OrderJDBC;
import db.PaymentJDBC;
import model.Order;
import model.Payment;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {

    private final PaymentJDBC paymentJDBC = new PaymentJDBC();
    private final OrderJDBC   orderJDBC   = new OrderJDBC();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String orderId   = req.getParameter("orderId");
        String paymentId = req.getParameter("paymentId");

        Order   order   = orderJDBC.findById(orderId);
        Payment payment = paymentJDBC.findById(paymentId);

        req.setAttribute("order", order);
        req.setAttribute("payment", payment);
        req.getRequestDispatcher("/WEB-INF/views/payment.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String paymentId = req.getParameter("paymentId");
        String orderId   = req.getParameter("orderId");

        // Update status payment dan order
        paymentJDBC.updateStatus(paymentId, "PAID");
        orderJDBC.updateStatus(orderId, "PROSES");

        res.sendRedirect(req.getContextPath() + "/dashboard?success=payment");
    }
}
