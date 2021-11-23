<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Redirecting...</title>
</head>
<body>
<p> welcome </p>
<%
if(session.getAttribute("usertype")==null)
	response.sendRedirect("index.jsp");
else if(session.getAttribute("usertype").equals("admin")) 
	response.sendRedirect("AdminIndex.jsp");
else if(session.getAttribute("usertype").equals("patient"))
	response.sendRedirect("PatientIndex.jsp");
else
	response.sendRedirect("DoctorIndex.jsp");

%>
</body>
</html>