<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Go-Laundry</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="auth-wrapper">
    <div class="auth-card">

        <div class="auth-logo">
            <h1>Go<span>Laundry</span></h1>
            <p>Masuk ke akun Anda</p>
        </div>

        <%-- Pesan error --%>
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger">${error}</div>
        <% } %>

        <%-- Pesan sukses setelah register --%>
        <% if ("1".equals(request.getParameter("success"))) { %>
            <div class="alert alert-success">Registrasi berhasil! Silakan login.</div>
        <% } %>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email"
                       class="form-control" placeholder="contoh@email.com" required>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password"
                       class="form-control" placeholder="Masukkan password" required>
            </div>

            <button type="submit" class="btn btn-primary">Masuk</button>
        </form>

        <div class="divider">atau</div>

        <p style="text-align:center; font-size:0.9rem; color:#6b7280;">
            Belum punya akun?
            <a href="${pageContext.request.contextPath}/register" class="link">Daftar sekarang</a>
        </p>

    </div>
</div>
</body>
</html>
