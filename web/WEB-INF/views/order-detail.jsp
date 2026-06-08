<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detail Order - Go-Laundry</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%@ include file="navbar.jsp" %>

<div class="container">
    <div class="page-header">
        <div>
            <h2>Detail Order</h2>
            <p>Informasi lengkap pesanan Anda</p>
        </div>
        <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-outline" style="width:auto;">
            ← Kembali
        </a>
    </div>

    <c:choose>
        <c:when test="${empty order}">
            <div class="alert alert-danger">Order tidak ditemukan.</div>
        </c:when>
        <c:otherwise>
            <div style="display:grid; grid-template-columns:1fr 1fr; gap:1.5rem;">

                <%-- Info Order --%>
                <div class="card">
                    <h3 style="margin-bottom:1.25rem; font-size:1.05rem; border-bottom:1px solid #e5e9f2; padding-bottom:0.75rem;">
                        Informasi Order
                    </h3>
                    <table style="font-size:0.95rem;">
                        <tr>
                            <td style="padding:0.5rem 0; color:#6b7280; width:140px;">Order ID</td>
                            <td style="padding:0.5rem 0;"><strong>${order.orderId}</strong></td>
                        </tr>
                        <tr>
                            <td style="padding:0.5rem 0; color:#6b7280;">Tanggal</td>
                            <td style="padding:0.5rem 0;">${order.tanggal}</td>
                        </tr>
                        <tr>
                            <td style="padding:0.5rem 0; color:#6b7280;">Berat</td>
                            <td style="padding:0.5rem 0;">${order.berat} kg</td>
                        </tr>
                        <tr>
                            <td style="padding:0.5rem 0; color:#6b7280;">Total Harga</td>
                            <td style="padding:0.5rem 0; font-weight:700; color:#1a6ef5; font-size:1.1rem;">
                                Rp ${order.totalHarga}
                            </td>
                        </tr>
                        <tr>
                            <td style="padding:0.5rem 0; color:#6b7280;">Status</td>
                            <td style="padding:0.5rem 0;">
                                <span class="badge badge-${order.status.toLowerCase()}">${order.status}</span>
                            </td>
                        </tr>
                    </table>
                </div>

                <%-- Aksi --%>
                <div class="card">
                    <h3 style="margin-bottom:1.25rem; font-size:1.05rem; border-bottom:1px solid #e5e9f2; padding-bottom:0.75rem;">
                        Aksi
                    </h3>

                    <c:if test="${order.status == 'PENDING'}">
                        <div class="alert alert-info" style="margin-bottom:1rem;">
                            Order menunggu konfirmasi admin.
                        </div>
                        <a href="${pageContext.request.contextPath}/order?action=cancel&id=${order.orderId}"
                           class="btn btn-danger"
                           style="width:100%; margin-bottom:0.75rem;"
                           onclick="return confirm('Yakin ingin membatalkan order ini?')">
                            Batalkan Order
                        </a>
                    </c:if>

                    <c:if test="${order.status == 'PROSES'}">
                        <div class="alert alert-info">
                            Laundry Anda sedang dikerjakan. Harap tunggu.
                        </div>
                    </c:if>

                    <c:if test="${order.status == 'SELESAI'}">
                        <div class="alert alert-success" style="margin-bottom:1rem;">
                            Laundry selesai! Berikan review Anda.
                        </div>
                        <%-- Form Review --%>
                        <form action="${pageContext.request.contextPath}/review" method="post">
                            <input type="hidden" name="serviceId" value="${order.serviceId}">
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
                    </c:if>

                    <c:if test="${order.status == 'CANCELLED'}">
                        <div class="alert alert-danger">Order ini telah dibatalkan.</div>
                    </c:if>
                </div>

            </div>
        </c:otherwise>
    </c:choose>
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
