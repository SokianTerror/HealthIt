<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<%@ include file="styling/navbar.jsp" %>  
</head>
<body>
<%
response.setHeader("Cache-Control","no-cache"); //index page for everyone
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
if(session.getAttribute("uname")==null || !session.getAttribute("usertype").equals("doctor"))
	response.sendRedirect("redirectionjsp.jsp");
	%>
<h2>Welcome <%=session.getAttribute("uname")%></h2>

<h2> Your amka is:<%=session.getAttribute("id")%></h2>

<h2>Your Fullname is: <%= session.getAttribute("surname")%></h2>
<form method=post action=addAppointment.jsp>
<h2> Add your appointments: </h2>
<input type=submit value="Add"> </form>

<form method=post action=cancelAppointmentDoctorServlet>
<h2> Cancel appointments: </h2>
<input type=submit value="Cancel"> </form>

<form method=post action=viewProgramsDoctorServlet>
<h2> View your next appointments: </h2>
<input type=submit value="View"> </form>
</body>
</html>