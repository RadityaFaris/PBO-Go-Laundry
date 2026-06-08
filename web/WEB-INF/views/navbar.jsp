<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Akun" %>
<%
    Akun akunNav = (Akun) session.getAttribute("akunLogin");
    String currentUri = request.getRequestURI();
%>
<nav class="navbar">
    <a href="${pageContext.request.contextPath}/dashboard" class="navbar-brand">
        Go<span>Laundry</span>
    </a>
    <div class="navbar-nav">
        <a href="${pageContext.request.contextPath}/dashboard"
           class="<%= currentUri.contains("dashboard") ? "active" : "" %>">Dashboard</a>
        <a href="${pageContext.request.contextPath}/order"
           class="<%= currentUri.contains("order") ? "active" : "" %>">Order</a>
        <a href="${pageContext.request.contextPath}/service"
           class="<%= currentUri.contains("service") ? "active" : "" %>">Layanan</a>
        <span style="color:#1a1f36; font-weight:600; font-size:0.9rem;">
            👤 <%= akunNav != null ? akunNav.getNama() : "User" %>
        </span>
        <a href="${pageContext.request.contextPath}/logout"
           style="color:#ef4444; font-weight:600;">Keluar</a>
    </div>
</nav>
