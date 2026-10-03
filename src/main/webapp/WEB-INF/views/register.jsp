<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Register - Eventora</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="navbar">
  <a href="${pageContext.request.contextPath}/" class="brand">Eventora</a>
  <a href="${pageContext.request.contextPath}/events">Events</a>
  <a href="${pageContext.request.contextPath}/login">Login</a>
  <a href="${pageContext.request.contextPath}/register">Register</a>
</div>

<div class="auth-box wide">
  <h2>Student Registration</h2>

  <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-error"><%= request.getAttribute("error") %></div>
  <% } %>

  <form action="${pageContext.request.contextPath}/register" method="post" id="regForm">

    <div class="form-group">
      <label>Full Name *</label>
      <input type="text" name="fullName" placeholder="e.g. Rahul Sharma" required
             value="<%= request.getParameter("fullName") != null ? request.getParameter("fullName") : "" %>">
    </div>

    <div class="form-group">
      <label>Roll Number *</label>
      <input type="text" name="rollNumber" placeholder="e.g. CS2024001" required
             value="<%= request.getParameter("rollNumber") != null ? request.getParameter("rollNumber") : "" %>">
    </div>

    <div class="form-group">
      <label>Email Address *</label>
      <input type="email" name="email" placeholder="you@university.edu" required
             value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>">
    </div>

    <div class="form-group">
      <label>Phone Number</label>
      <input type="tel" name="phone" placeholder="e.g. 9876543210">
    </div>

    <div class="form-group">
      <label>Department *</label>
      <select name="department" required>
        <option value="" disabled selected>-- Select Department --</option>
        <option>Computer Science</option>
        <option>Information Technology</option>
        <option>Electronics &amp; Communication</option>
        <option>Mechanical Engineering</option>
        <option>Civil Engineering</option>
        <option>Business Administration</option>
        <option>Arts &amp; Humanities</option>
      </select>
    </div>

    <div class="form-group">
      <label>Password * (min 6 characters)</label>
      <input type="password" name="password" id="pwd" placeholder="Enter password" required>
    </div>

    <div class="form-group">
      <label>Confirm Password *</label>
      <input type="password" name="confirmPassword" id="cpwd" placeholder="Repeat password" required>
    </div>

    <br>
    <button type="submit" class="btn btn-green" style="width:100%">Create Account</button>
  </form>

  <div class="auth-footer">
    <br>
    Already have an account? <a href="${pageContext.request.contextPath}/login">Login here</a>
  </div>
</div>

<footer>&copy; 2026 Eventora - Campus Event &amp; Student Registration Platform</footer>

<script>
  document.getElementById('regForm').addEventListener('submit', function(e) {
    var p = document.getElementById('pwd').value;
    var c = document.getElementById('cpwd').value;
    if (p.length < 6) { e.preventDefault(); alert('Password must be at least 6 characters!'); return; }
    if (p !== c)       { e.preventDefault(); alert('Passwords do not match!'); }
  });
</script>
</body>
</html>
