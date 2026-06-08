<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.ServiceLaundry" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buat Order - Go-Laundry</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>

<%@ include file="navbar.jsp" %>

<div class="container">
    <div class="page-header">
        <div>
            <h2>Buat Order Baru</h2>
            <p>Pilih layanan dan masukkan berat laundry</p>
        </div>
        <a href="<%= request.getContextPath() %>/dashboard" class="btn btn-outline" style="width:auto;">
            ← Kembali
        </a>
    </div>

    <div style="display:grid; grid-template-columns: 1fr 360px; gap:1.5rem; align-items:start;">

        <%-- Pilih Layanan tanpa JSTL --%>
        <div class="card">
            <h3 style="margin-bottom:1.25rem; font-size:1.05rem;">Pilih Layanan</h3>
            <div class="service-grid">
                <%
                    List<ServiceLaundry> services = (List<ServiceLaundry>) request.getAttribute("services");
                    if (services != null) {
                        for (ServiceLaundry s : services) {
                %>
                    <div class="service-card" 
                         onclick="pilihService('<%= s.getId() %>', '<%= s.getJenisLayanan() %>', <%= s.getHargaPerKg() %>)"
                         id="card-<%= s.getId() %>">
                        <div style="font-size:1.6rem; margin-bottom:0.5rem;">🧺</div>
                        <h3><%= s.getJenisLayanan() %></h3>
                        <div class="price">Rp <%= s.getHargaPerKg() %>/kg</div>
                        <div class="est">⏱ Estimasi: <%= s.getEstimasiWaktu() %> jam</div>
                    </div>
                <%
                        }
                    }
                %>
            </div>
        </div>

        <%-- Form Order --%>
        <div class="card">
            <h3 style="margin-bottom:1.25rem; font-size:1.05rem;">Detail Order</h3>

            <form action="<%= request.getContextPath() %>/order" method="post" id="formOrder">
                <input type="hidden" name="serviceId" id="serviceId">

                <div class="form-group">
                    <label>Layanan Dipilih</label>
                    <input type="text" id="namaLayanan" class="form-control"
                           placeholder="Klik layanan di sebelah kiri" readonly
                           style="background:#f4f7fc; cursor:not-allowed;">
                </div>

                <div class="form-group">
                    <label for="berat">Berat Laundry (kg)</label>
                    <input type="number" id="berat" name="berat"
                           class="form-control" placeholder="Contoh: 3.5"
                           min="0.5" step="0.5" oninput="hitungHarga()">
                </div>

                <div class="form-group">
                    <label>Estimasi Harga</label>
                    <div id="estimasiHarga" style="
                        padding: 0.75rem 1rem;
                        background: #f0f5ff;
                        border: 1.5px solid #bfdbfe;
                        border-radius: 8px;
                        font-size: 1.2rem;
                        font-weight: 700;
                        color: #1a6ef5;">
                        Rp 0
                    </div>
                </div>

                <button type="submit" class="btn btn-primary" id="btnOrder" disabled>
                    Buat Order
                </button>
            </form>
        </div>

    </div>
</div>

<script>
    let hargaPerKg = 0;

    function pilihService(id, nama, harga) {
        // Reset semua card
        document.querySelectorAll('.service-card').forEach(c => c.classList.remove('selected'));
        // Highlight card yang dipilih
        document.getElementById('card-' + id).classList.add('selected');

        document.getElementById('serviceId').value   = id;
        document.getElementById('namaLayanan').value = nama;
        hargaPerKg = harga;

        hitungHarga();
    }

    function hitungHarga() {
        const berat = parseFloat(document.getElementById('berat').value) || 0;
        const total = berat * hargaPerKg;
        document.getElementById('estimasiHarga').textContent =
            'Rp ' + total.toLocaleString('id-ID');

        const siap = document.getElementById('serviceId').value && berat > 0;
        document.getElementById('btnOrder').disabled = !siap;
    }
</script>
</body>
</html>
