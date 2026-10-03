<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.eventora.model.Student,com.eventora.model.Event,java.util.List,java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Events - Eventora</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%
  // Guard: redirect to login if not authenticated
  Student student = (Student) session.getAttribute("student");
  if (student == null) {
      response.sendRedirect(request.getContextPath() + "/login");
      return;
  }
  List<Event> events = (List<Event>) request.getAttribute("events");
  String msg = request.getParameter("msg");
  SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
  SimpleDateFormat tf = new SimpleDateFormat("hh:mm a");
%>

<div class="navbar">
  <a href="${pageContext.request.contextPath}/" class="brand">Eventora</a>
  <a href="${pageContext.request.contextPath}/events">Events</a>
  <a href="${pageContext.request.contextPath}/dashboard">My Dashboard</a>
  <a href="${pageContext.request.contextPath}/logout" style="float:right">
    Logout (<%= student.getFullName().split(" ")[0] %>)
  </a>
</div>

<div class="container">
  <h1>Upcoming Events</h1>

  <% if ("success".equals(msg)) { %>
    <div class="alert alert-success">You have successfully registered for the event!</div>
  <% } else if ("duplicate".equals(msg)) { %>
    <div class="alert alert-info">You are already registered for this event.</div>
  <% } else if ("full".equals(msg)) { %>
    <div class="alert alert-error">Sorry, this event is full.</div>
  <% } else if ("error".equals(msg)) { %>
    <div class="alert alert-error">Something went wrong. Please try again.</div>
  <% } %>

  <% if (events == null || events.isEmpty()) { %>
    <p>No events available at the moment.</p>
  <% } else {
       for (Event ev : events) {
         boolean alreadyReg = Boolean.TRUE.equals(request.getAttribute("reg_" + ev.getEventId()));
  %>
  <div class="event-card">
    <h3><%= ev.getTitle() %></h3>
    <p><%= ev.getDescription() %></p>
    <p class="meta">
      <b>Category:</b> <%= ev.getCategory() %> &nbsp;|&nbsp;
      <b>Date:</b> <%= df.format(ev.getEventDate()) %> &nbsp;|&nbsp;
      <b>Time:</b> <%= tf.format(ev.getEventTime()) %> &nbsp;|&nbsp;
      <b>Venue:</b> <%= ev.getVenue() %>
    </p>
    <p class="meta">
      <b>Seats Available:</b> <%= ev.getSlotsLeft() %> / <%= ev.getCapacity() %>
    </p>
    <div class="cap-bar">
      <div class="cap-fill" style="width:<%= ev.getFillPercent() %>%"></div>
    </div>

    <% if (alreadyReg) { %>
      <span class="badge badge-green">&#10003; Registered</span>
    <% } else if (!ev.hasSlotsAvailable()) { %>
      <span class="badge badge-red">Event Full</span>
    <% } else { %>
      <form action="${pageContext.request.contextPath}/events/register" method="post" style="display:inline">
        <input type="hidden" name="eventId" value="<%= ev.getEventId() %>">
        <button type="submit" class="btn btn-blue"
                onclick="return confirm('Register for this event?')">Register</button>
      </form>
    <% } %>
  </div>
  <% } } %>

</div>

<footer>&copy; 2026 Eventora - Campus Event &amp; Student Registration Platform</footer>
</body>
</html>
