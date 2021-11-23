<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Home
</title>
</head>
<body>
<%@ include file="styling/navbar.jsp" %>  
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
if(session.getAttribute("uname")==null || !session.getAttribute("usertype").equals("patient"))
	response.sendRedirect("redirectionjsp.jsp");
	%>
<h2>Welcome <%=session.getAttribute("uname")%></h2>

<h2> Your amka is:<%=session.getAttribute("id")%></h2>

<h2>Your Fullname is: <%= session.getAttribute("surname")%></h2>

<form method=post action=bookAppointmentServlet>
<h2> Book appointment here: </h2>
<input type=submit value=Book> </form>

<form method=post action=viewProgramsServlet>
<h2> Appointment history here: </h2>
<input type=submit value=history> </form>

<form method=post action=cancelAppointmentServlet>
<h2> Cancel your appointments: </h2>
<input type=submit value="View appointments"> </form>

</body>
</html>