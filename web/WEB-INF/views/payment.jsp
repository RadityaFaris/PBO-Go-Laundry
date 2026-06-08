<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pembayaran - Go-Laundry</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%@ include file="navbar.jsp" %>

<div class="container" style="max-width:520px;">
    <div class="page-header">
        <div>
            <h2>Pembayaran</h2>
            <p>Selesaikan pembayaran order Anda</p>
        </div>
    </div>

    <div class="card">
        <div style="text-align:center; padding:1rem 0 1.5rem;">
            <div style="font-size:3rem; margin-bottom:0.5rem;">💳</div>
            <h3 style="font-size:1.1rem; color:#6b7280;">Total Pembayaran</h3>
            <div style="font-size:2.5rem; font-weight:700; color:#1a6ef5; margin-top:0.25rem;">
                Rp ${payment.amount}
            </div>
        </div>

        <table style="font-size:0.9rem; margin-bottom:1.5rem; width:100%;">
            <tr>
                <td style="padding:0.5rem 0; color:#6b7280;">Order ID</td>
                <td style="padding:0.5rem 0; text-align:right;"><strong>${order.orderId}</strong></td>
            </tr>
            <tr>
                <td style="padding:0.5rem 0; color:#6b7280;">Payment ID</td>
                <td style="padding:0.5rem 0; text-align:right;">${payment.id}</td>
            </tr>
            <tr>
                <td style="padding:0.5rem 0; color:#6b7280;">Status</td>
                <td style="padding:0.5rem 0; text-align:right;">
                    <span class="badge badge-${payment.status.toLowerCase()}">${payment.status}</span>
                </td>
            </tr>
        </table>

        <c:if test="${payment.status == 'PENDING'}">
            <form action="${pageContext.request.contextPath}/payment" method="post">
                <input type="hidden" name="paymentId" value="${payment.id}">
                <input type="hidden" name="orderId"   value="${order.orderId}">
                <button type="submit" class="btn btn-primary"
                        onclick="return confirm('Konfirmasi pembayaran sebesar Rp ${payment.amount}?')">
                    Konfirmasi Pembayaran
                </button>
            </form>
        </c:if>

        <c:if test="${payment.status == 'PAID'}">
            <div class="alert alert-success" style="text-align:center;">
                ✅ Pembayaran sudah dikonfirmasi
            </div>
        </c:if>

        <a href="${pageContext.request.contextPath}/dashboard"
           class="btn btn-outline" style="width:100%; margin-top:0.75rem;">
            Kembali ke Dashboard
        </a>
    </div>
</div>
</body>
</html>
