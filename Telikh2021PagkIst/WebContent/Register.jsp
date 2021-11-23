<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<%@ include file="styling/navbar.jsp" %>
</head>
<body>
<h1>Register:</h1>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);

if(session.getAttribute("uname")==null){ //if session exists redirect to Patient Index otherwise print a login form
	%><br><br>
	<form method="post" action="registerServlet"> 
	<h2> Username:</h2>
	<input type=text value=username name=username required> 
	<h2> Name:</h2>
	<input type=text value=name name=name required> 
	<h2> Surname:</h2>
	<input type=text value=surname name=surname required> 
	<h2> Password:</h2>
	<input type=password value=Password name =password required> 
	<input type=submit value=login>
	</form><%
} else{
	response.sendRedirect("PatientIndex.jsp");
}%>
<a style href=Register.jsp>Not have a account? Click here!</a>


</body>
</html>