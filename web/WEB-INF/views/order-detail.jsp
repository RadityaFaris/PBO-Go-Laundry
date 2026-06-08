<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Order" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detail Order - Go-Laundry</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>

<%@ include file="navbar.jsp" %>

<div class="container">
    <div class="page-header">
        <div>
            <h2>Detail Order</h2>
            <p>Informasi lengkap pesanan Anda</p>
        </div>
        <a href="<%= request.getContextPath() %>/dashboard" class="btn btn-outline" style="width:auto;">
            ← Kembali
        </a>
    </div>

    <%
        Order order = (Order) request.getAttribute("order");
        if (order == null) {
    %>
        <div class="alert alert-danger">Order tidak ditemukan.</div>
    <%
        } else {
    %>
        <div style="display:grid; grid-template-columns:1fr 1fr; gap:1.5rem;">

            <%-- Info Order --%>
            <div class="card">
                <h3 style="margin-bottom:1.25rem; font-size:1.05rem; border-bottom:1px solid #e5e9f2; padding-bottom:0.75rem;">
                    Informasi Order
                </h3>
                <table style="font-size:0.95rem;">
                    <tr>
                        <td style="padding:0.5rem 0; color:#6b7280; width:140px;">Order ID</td>
                        <td style="padding:0.5rem 0;"><strong><%= order.getOrderId() %></strong></td>
                    </tr>
                    <tr>
                        <td style="padding:0.5rem 0; color:#6b7280;">Tanggal</td>
                        <td style="padding:0.5rem 0;"><%= order.getTanggal() %></td>
                    </tr>
                    <tr>
                        <td style="padding:0.5rem 0; color:#6b7280;">Berat</td>
                        <td style="padding:0.5rem 0;"><%= order.getBerat() %> kg</td>
                    </tr>
                    <tr>
                        <td style="padding:0.5rem 0; color:#6b7280;">Total Harga</td>
                        <td style="padding:0.5rem 0; font-weight:700; color:#1a6ef5; font-size:1.1rem;">
                            Rp <%= order.getTotalHarga() %>
                        </td>
                    </tr>
                    <tr>
                        <td style="padding:0.5rem 0; color:#6b7280;">Status</td>
                        <td style="padding:0.5rem 0;">
                            <span class="badge badge-<%= order.getStatus().toLowerCase() %>"><%= order.getStatus() %></span>
                        </td>
                    </tr>
                </table>
            </div>

            <%-- Aksi --%>
            <div class="card">
                <h3 style="margin-bottom:1.25rem; font-size:1.05rem; border-bottom:1px solid #e5e9f2; padding-bottom:0.75rem;">
                    Aksi
                </h3>

                <%
                    String status = order.getStatus();
                    if ("PENDING".equals(status)) {
                %>
                    <div class="alert alert-info" style="margin-bottom:1rem;">
                        Order menunggu konfirmasi admin.
                    </div>
                    <a href="<%= request.getContextPath() %>/order?action=cancel&id=<%= order.getOrderId() %>"
                       class="btn btn-danger"
                       style="width:100%; margin-bottom:0.75rem;"
                       onclick="return confirm('Yakin ingin membatalkan order ini?')">
                        Batalkan Order
                    </a>
                <%
                    } else if ("PROSES".equals(status)) {
                %>
                    <div class="alert alert-info">
                        Laundry Anda sedang dikerjakan. Harap tunggu.
                    </div>
                <%
                    } else if ("SELESAI".equals(status)) {
                %>
                    <div class="alert alert-success" style="margin-bottom:1rem;">
                        Laundry selesai! Berikan review Anda.
                    </div>
                    <%-- Form Review --%>
                    <form action="<%= request.getContextPath() %>/review" method="post">
                        <input type="hidden" name="serviceId" value="<%= order.getServiceId() %>">
                        <div class="form-group">
                            <label>Rating</label>
                            <div class="star-rating" id="starRating">
                                <span class="star" data-val="1">★</span>
                                <span class="star" data-val="2">★</span>
                                <span class="star" data-val="3">★</span>
                                <span class="star" data-val="4">★</span>
                                <span class="star" data-val="5">★</span>
                            </div>
                            <input type="hidden" name="rating" id="ratingVal" value="0">
                        </div>
                        <div class="form-group">
                            <label for="comment">Komentar</label>
                            <textarea id="comment" name="comment" class="form-control"
                                      rows="3" placeholder="Ceritakan pengalaman Anda..."></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">Kirim Review</button>
                    </form>
                <%
                    } else if ("CANCELLED".equals(status)) {
                %>
                    <div class="alert alert-danger">Order ini telah dibatalkan.</div>
                <%
                    }
                %>
            </div>

        </div>
    <%
        }
    %>
</div>

<script>
    // Star rating interaktif
    const stars = document.querySelectorAll('.star');
    stars.forEach(star => {
        star.addEventListener('click', () => {
            const val = parseInt(star.dataset.val);
            document.getElementById('ratingVal').value = val;
            stars.forEach((s, i) => {
                s.classList.toggle('active', i < val);
            });
        });
    });
</script>
</body>
</html>
