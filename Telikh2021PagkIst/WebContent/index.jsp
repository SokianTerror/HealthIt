<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<%@ include file="styling/navbar.jsp" %>  
</head>
<body>
<p> Index Page </p>
<% 
response.setHeader("Cache-Control","no-cache"); //index page for everyone
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
if(session.getAttribute("uname")== null  ){ //if uname is clear print click here to login, otherwise redirect to patientIndex
	out.write("<a style=color:black; href='login.jsp'>Click here to login!</a>");
	}else{ 

		response.sendRedirect("redirectionjsp.jsp");}
%>
	
</body>
</html>