<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.eventora.model.Student,com.eventora.model.Event,java.util.List,java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>My Dashboard - Eventora</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%
  Student student  = (Student) request.getAttribute("student");
  List<Event> myEvents = (List<Event>) request.getAttribute("myEvents");
  SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
  SimpleDateFormat tf = new SimpleDateFormat("hh:mm a");
%>

<div class="navbar">
  <a href="${pageContext.request.contextPath}/" class="brand">Eventora</a>
  <a href="${pageContext.request.contextPath}/events">Events</a>
  <a href="${pageContext.request.contextPath}/dashboard">My Dashboard</a>
  <a href="${pageContext.request.contextPath}/logout" style="float:right">Logout</a>
</div>

<div class="container">

  <h1>My Dashboard</h1>

  <!-- Student Info -->
  <table style="width:auto; margin-bottom:20px; border:none;">
    <tr>
      <td style="border:none; padding:5px 8px;"><b>Name</b></td>
      <td style="border:none; padding:5px 4px;">&nbsp;:&nbsp;</td>
      <td style="border:none; padding:5px 8px;"><%= student.getFullName() %></td>
    </tr>
    <tr>
      <td style="border:none; padding:5px 8px;"><b>Roll Number</b></td>
      <td style="border:none; padding:5px 4px;">&nbsp;:&nbsp;</td>
      <td style="border:none; padding:5px 8px;"><%= student.getRollNumber() %></td>
    </tr>
    <tr>
      <td style="border:none; padding:5px 8px;"><b>Department</b></td>
      <td style="border:none; padding:5px 4px;">&nbsp;:&nbsp;</td>
      <td style="border:none; padding:5px 8px;"><%= student.getDepartment() %></td>
    </tr>
    <tr>
      <td style="border:none; padding:5px 8px;"><b>Email</b></td>
      <td style="border:none; padding:5px 4px;">&nbsp;:&nbsp;</td>
      <td style="border:none; padding:5px 8px;"><%= student.getEmail() %></td>
    </tr>
  </table>

  <hr style="border:none; border-top:1px solid #ddd; margin:16px 0;">

  <!-- Stats -->
  <div class="stats-row">
    <div class="stat-box">
      <div class="num"><%= myEvents != null ? myEvents.size() : 0 %></div>
      <div class="lbl">Events Registered</div>
    </div>
    <div class="stat-box">
      <div class="num" style="font-size:16px; line-height:1.4;"><%= student.getDepartment() %></div>
      <div class="lbl">Department</div>
    </div>
  </div>

  <hr style="border:none; border-top:1px solid #ddd; margin:16px 0;">

  <h2>My Registered Events</h2>

  <% if (myEvents == null || myEvents.isEmpty()) { %>
    <div class="alert alert-info">
      You have not registered for any events yet.
      &nbsp;<a href="${pageContext.request.contextPath}/events">Browse Events &rarr;</a>
    </div>
  <% } else { %>
    <table>
      <thead>
        <tr>
          <th>#</th>
          <th>Event Name</th>
          <th>Category</th>
          <th>Date</th>
          <th>Time</th>
          <th>Venue</th>
          <th>Status</th>
        </tr>
      </thead>
      <tbody>
        <% for (int i = 0; i < myEvents.size(); i++) {
             Event ev = myEvents.get(i); %>
        <tr>
          <td><%= i + 1 %></td>
          <td><b><%= ev.getTitle() %></b></td>
          <td><%= ev.getCategory() %></td>
          <td><%= df.format(ev.getEventDate()) %></td>
          <td><%= tf.format(ev.getEventTime()) %></td>
          <td><%= ev.getVenue() %></td>
          <td><span class="badge badge-green">&#10003; Confirmed</span></td>
        </tr>
        <% } %>
      </tbody>
    </table>
  <% } %>

  <br>
  <a href="${pageContext.request.contextPath}/events" class="btn btn-blue">Browse More Events</a>

</div>

<footer>&copy; 2026 Eventora - Campus Event &amp; Student Registration Platform</footer>
</body>
</html>
