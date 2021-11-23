<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="styling/navbar.jsp" %>  
<title>Add Appointment</title>
</head>
<body>
<%
response.setHeader("Cache-Control","no-cache"); //index page for everyone
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
if(session.getAttribute("uname")==null || !session.getAttribute("usertype").equals("doctor")){
	response.sendRedirect("redirectionjsp.jsp");
}%>
<form method=post action="addAppointmentServlet">
  <label for="cal">Select date:</label>
  <input type="date" id="cal" name="cal" required>
  <br>
  <label for="time">Select time:</label>
  <input type="time" id="time" name="time"
       min="09:00" max="22:00" value="18:00" required>
  <input type="submit">
</form>
<script>A
var today = new Date().toISOString().split('T')[0];
document.getElementsByName("cal")[0].setAttribute('min', today);
</script>
</body>
</html>