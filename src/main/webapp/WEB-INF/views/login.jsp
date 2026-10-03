<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login - Eventora</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="navbar">
  <a href="${pageContext.request.contextPath}/" class="brand">Eventora</a>
  <a href="${pageContext.request.contextPath}/events">Events</a>
  <a href="${pageContext.request.contextPath}/login">Login</a>
  <a href="${pageContext.request.contextPath}/register">Register</a>
</div>

<div class="auth-box">
  <h2>Student Login</h2>

  <% if ("true".equals(request.getParameter("registered"))) { %>
    <div class="alert alert-success">Account created successfully! Please login.</div>
  <% } %>
  <% if ("true".equals(request.getParameter("logout"))) { %>
    <div class="alert alert-info">You have been logged out.</div>
  <% } %>
  <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-error"><%= request.getAttribute("error") %></div>
  <% } %>

  <form action="${pageContext.request.contextPath}/login" method="post">
    <div class="form-group">
      <label>Email Address</label>
      <input type="email" name="email" placeholder="Enter your email" required>
    </div>
    <div class="form-group">
      <label>Password</label>
      <input type="password" name="password" placeholder="Enter your password" required>
    </div>
    <br>
    <button type="submit" class="btn btn-blue" style="width:100%">Login</button>
  </form>

  <div class="auth-footer">
    <br>
    Don't have an account? <a href="${pageContext.request.contextPath}/register">Register here</a>
  </div>
</div>

<footer>&copy; 2026 Eventora - Campus Event &amp; Student Registration Platform</footer>
</body>
</html>
