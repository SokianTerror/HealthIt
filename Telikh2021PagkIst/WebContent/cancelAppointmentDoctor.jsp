<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cancel Appointment</title>
<%@ include file="styling/navbar.jsp" %>  

</head>
<body>
<%response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
if(session.getAttribute("uname")==null || !session.getAttribute("usertype").equals("doctor"))
	response.sendRedirect("redirectionjsp.jsp");
	 %>
</body>
</html>