<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Akun" %>
<%@ page import="model.ServiceLaundry" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Layanan - Go-Laundry</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
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

    <%-- Daftar Service tanpa JSTL --%>
    <div class="service-grid" style="margin-bottom:2rem;">
        <%
            List<ServiceLaundry> services = (List<ServiceLaundry>) request.getAttribute("services");
            if (services == null || services.isEmpty()) {
        %>
            <div class="alert alert-info">Belum ada layanan tersedia.</div>
        <%
            } else {
                for (ServiceLaundry s : services) {
        %>
            <div class="service-card">
                <div style="font-size:2rem; margin-bottom:0.75rem;">🧺</div>
                <h3><%= s.getJenisLayanan() %></h3>
                <div class="price">Rp <%= s.getHargaPerKg() %>/kg</div>
                <div class="est">⏱ Estimasi: <%= s.getEstimasiWaktu() %> jam</div>
                <div style="margin-top:0.75rem;">
                    <%
                        if (s.isAvailable()) {
                    %>
                        <span class="badge badge-selesai">Tersedia</span>
                    <%
                        } else {
                    %>
                        <span class="badge badge-cancelled">Tidak Tersedia</span>
                    <%
                        }
                    %>
                </div>
            </div>
        <%
                }
            }
        %>
    </div>

    <%-- Form Tambah Service (Admin Only) --%>
    <%
        Akun akunSvc = (Akun) session.getAttribute("akunLogin");
    %>

    <div class="card" style="max-width:480px;">
        <h3 style="margin-bottom:1.25rem; font-size:1.05rem;">Tambah Layanan Baru</h3>
        <form action="<%= request.getContextPath() %>/service" method="post">

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
