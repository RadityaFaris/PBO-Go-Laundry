<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Akun" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Layanan - Go-Laundry</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%@ include file="navbar.jsp" %>

<div class="container">
    <div class="page-header">
        <div>
            <h2>Daftar Layanan</h2>
            <p>Semua layanan laundry yang tersedia</p>
        </div>
    </div>

    <%-- Daftar Service --%>
    <div class="service-grid" style="margin-bottom:2rem;">
        <c:choose>
            <c:when test="${empty services}">
                <div class="alert alert-info">Belum ada layanan tersedia.</div>
            </c:when>
            <c:otherwise>
                <c:forEach var="s" items="${services}">
                    <div class="service-card">
                        <div style="font-size:2rem; margin-bottom:0.75rem;">🧺</div>
                        <h3>${s.jenisLayanan}</h3>
                        <div class="price">Rp ${s.hargaPerKg}/kg</div>
                        <div class="est">⏱ Estimasi: ${s.estimasiWaktu} jam</div>
                        <div style="margin-top:0.75rem;">
                            <c:choose>
                                <c:when test="${s.available}">
                                    <span class="badge badge-selesai">Tersedia</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-cancelled">Tidak Tersedia</span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>

    <%-- Form Tambah Service (Admin Only) --%>
    <%
        model.Akun akunSvc = (model.Akun) session.getAttribute("akunLogin");
    %>

    <div class="card" style="max-width:480px;">
        <h3 style="margin-bottom:1.25rem; font-size:1.05rem;">Tambah Layanan Baru</h3>
        <form action="${pageContext.request.contextPath}/service" method="post">

            <div class="form-group">
                <label for="jenisLayanan">Jenis Layanan</label>
                <input type="text" id="jenisLayanan" name="jenisLayanan"
                       class="form-control" placeholder="Contoh: Cuci Kering" required>
            </div>

            <div class="form-group">
                <label for="hargaPerKg">Harga per Kg (Rp)</label>
                <input type="number" id="hargaPerKg" name="hargaPerKg"
                       class="form-control" placeholder="Contoh: 6000" min="1000" required>
            </div>

            <div class="form-group">
                <label for="estimasiWaktu">Estimasi Waktu (jam)</label>
                <input type="number" id="estimasiWaktu" name="estimasiWaktu"
                       class="form-control" placeholder="Contoh: 24" min="1" required>
            </div>

            <button type="submit" class="btn btn-primary">Tambah Layanan</button>
        </form>
    </div>

</div>
</body>
</html>
