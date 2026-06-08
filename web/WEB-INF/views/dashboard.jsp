<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Go-Laundry</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%@ include file="navbar.jsp" %>

<div class="container">

    <%-- Pesan sukses --%>
    <c:if test="${param.success == 'order'}">
        <div class="alert alert-success">Order berhasil dibuat!</div>
    </c:if>
    <c:if test="${param.success == 'payment'}">
        <div class="alert alert-success">Pembayaran berhasil diproses!</div>
    </c:if>
    <c:if test="${param.success == 'review'}">
        <div class="alert alert-success">Review berhasil dikirim!</div>
    </c:if>

    <div class="page-header">
        <div>
            <h2>Dashboard</h2>
            <p>Selamat datang, <strong>${akun.nama}</strong></p>
        </div>
        <a href="${pageContext.request.contextPath}/order" class="btn btn-primary" style="width:auto;">
            + Buat Order
        </a>
    </div>

    <%-- Stat Cards --%>
    <%
        List<Order> orders = (List<Order>) request.getAttribute("orders");
        int total = orders != null ? orders.size() : 0;
        int pending = 0, proses = 0, selesai = 0;
        if (orders != null) {
            for (Order o : orders) {
                if ("PENDING".equals(o.getStatus())) pending++;
                else if ("PROSES".equals(o.getStatus())) proses++;
                else if ("SELESAI".equals(o.getStatus())) selesai++;
            }
        }
    %>
    <div class="stats-grid">
        <div class="stat-card">
            <div class="stat-icon blue">📦</div>
            <div class="stat-info">
                <h3><%= total %></h3>
                <p>Total Order</p>
            </div>
        </div>
        <div class="stat-card">
            <div class="stat-icon yellow">⏳</div>
            <div class="stat-info">
                <h3><%= pending %></h3>
                <p>Menunggu</p>
            </div>
        </div>
        <div class="stat-card">
            <div class="stat-icon blue">🔄</div>
            <div class="stat-info">
                <h3><%= proses %></h3>
                <p>Diproses</p>
            </div>
        </div>
        <div class="stat-card">
            <div class="stat-icon green">✅</div>
            <div class="stat-info">
                <h3><%= selesai %></h3>
                <p>Selesai</p>
            </div>
        </div>
    </div>

    <%-- Tabel Order --%>
    <div class="card">
        <div class="page-header" style="margin-bottom:1rem;">
            <h2 style="font-size:1.1rem;">Riwayat Order</h2>
        </div>

        <div class="table-wrap">
            <table>
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Tanggal</th>
                        <th>Berat</th>
                        <th>Total Harga</th>
                        <th>Status</th>
                        <th>Aksi</th>
                    </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${empty orders}">
                        <tr>
                            <td colspan="6" style="text-align:center; color:#6b7280; padding:2rem;">
                                Belum ada order. <a href="${pageContext.request.contextPath}/order" class="link">Buat order sekarang</a>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="o" items="${orders}">
                            <tr>
                                <td><strong>${o.orderId}</strong></td>
                                <td>${o.tanggal}</td>
                                <td>${o.berat} kg</td>
                                <td>Rp <fmt:formatNumber value="${o.totalHarga}" pattern="#,###"/></td>
                                <td>
                                    <span class="badge badge-${o.status.toLowerCase()}">
                                        ${o.status}
                                    </span>
                                </td>
                                <td style="display:flex; gap:0.5rem;">
                                    <a href="${pageContext.request.contextPath}/order?action=detail&id=${o.orderId}"
                                       class="btn btn-outline btn-sm">Detail</a>
                                    <c:if test="${o.status == 'PENDING'}">
                                        <a href="${pageContext.request.contextPath}/order?action=cancel&id=${o.orderId}"
                                           class="btn btn-danger btn-sm"
                                           onclick="return confirm('Batalkan order ini?')">Batal</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
    </div>

</div>
</body>
</html>
