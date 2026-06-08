package servlet;

import db.ReviewJDBC;
import db.ServiceLaundryJDBC;
import model.Akun;
import model.Review;
import model.ServiceLaundry;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/review")
public class ReviewServlet extends HttpServlet {

    private final ReviewJDBC         reviewJDBC  = new ReviewJDBC();
    private final ServiceLaundryJDBC serviceJDBC = new ServiceLaundryJDBC();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("akunLogin") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        Akun akun = (Akun) session.getAttribute("akunLogin");

        String serviceId       = req.getParameter("serviceId");
        int    rating          = Integer.parseInt(req.getParameter("rating"));
        String comment         = req.getParameter("comment");
        ServiceLaundry service = serviceJDBC.findById(serviceId);

        String id  = "REV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Review rev = new Review(id, akun, service, rating, comment);
        reviewJDBC.insert(rev);

        res.sendRedirect(req.getContextPath() + "/dashboard?success=review");
    }
}
