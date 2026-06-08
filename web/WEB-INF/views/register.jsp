<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Daftar - Go-Laundry</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="auth-wrapper">
    <div class="auth-card">

        <div class="auth-logo">
            <h1>Go<span>Laundry</span></h1>
            <p>Buat akun baru</p>
        </div>

        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger">${error}</div>
        <% } %>

        <form action="${pageContext.request.contextPath}/register" method="post">
            <div class="form-group">
                <label for="nama">Nama Lengkap</label>
                <input type="text" id="nama" name="nama"
                       class="form-control" placeholder="Nama lengkap Anda" required>
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email"
                       class="form-control" placeholder="contoh@email.com" required>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password"
                       class="form-control" placeholder="Minimal 6 karakter" required minlength="6">
            </div>

            <button type="submit" class="btn btn-primary">Daftar</button>
        </form>

        <div class="divider">atau</div>

        <p style="text-align:center; font-size:0.9rem; color:#6b7280;">
            Sudah punya akun?
            <a href="${pageContext.request.contextPath}/login" class="link">Masuk di sini</a>
        </p>

    </div>
</div>
</body>
</html>
