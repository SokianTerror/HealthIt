<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<%@ include file="styling/navbar.jsp" %>  
</head>
<body>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);

if(session.getAttribute("uname")==null){ //if session exists redirect to Patient Index otherwise print a login form
	%><h2>Hello user!</h2> <br><br>
<h2> FIll your the field below to login.</h2>
	<form method="post" action="loginServlet"> 
	<h2> Name:</h2>
	<input type=text value=Username name=uname required> 
	<h2> Password:</h2>
	<input type=password value=Password name =password required> 
	<input type=submit value=login>
	</form><%
} else{
	response.sendRedirect("PatientIndex.jsp");
}%>
<a style=color:black; href=Register.jsp>Not have a account? Click here!</a>
</body>
</html>