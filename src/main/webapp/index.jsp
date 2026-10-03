<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Eventora - Campus Events</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="navbar">
  <a href="${pageContext.request.contextPath}/" class="brand">Eventora</a>
  <a href="${pageContext.request.contextPath}/events">Events</a>
  <a href="${pageContext.request.contextPath}/login">Login</a>
  <a href="${pageContext.request.contextPath}/register">Register</a>
</div>

<div class="hero">
  <h1>Campus Event Registration</h1>
  <p>Browse and register for events happening at your university.</p>
  <a href="${pageContext.request.contextPath}/register" class="btn btn-green">Register Now</a>
  &nbsp;
  <a href="${pageContext.request.contextPath}/login" class="btn btn-gray">Login</a>
</div>

<div class="container">
  <h2>About Eventora</h2>
  <p>Eventora is a campus event management platform where students can:</p>
  <ul style="margin-top:10px; line-height:2; font-size:14px;">
    <li>Browse upcoming campus events</li>
    <li>Register for events with one click</li>
    <li>Track registered events on your dashboard</li>
  </ul>
  <br>
  <a href="${pageContext.request.contextPath}/register" class="btn btn-blue">Create Account</a>
  &nbsp;
  <a href="${pageContext.request.contextPath}/login" class="btn btn-gray">Already have account? Login</a>
</div>

<footer>&copy; 2026 Eventora - Campus Event &amp; Student Registration Platform</footer>
</body>
</html>
