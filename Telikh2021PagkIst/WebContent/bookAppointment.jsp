<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Appointment</title>
<%@ include file="styling/navbar.jsp" %>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@ page import ="third.xrhstes.Doctor"%>
<%@ page import ="third.servlets.bookAppointmentServlet"%>
</head>
<body>
<% ArrayList<Doctor> docs = (ArrayList<Doctor>) request.getAttribute("doctorList");
if(!docs.isEmpty()){
	%> 
	<table border=1><tr><thead> Choose a speciality: </thead></tr><tr>
<%ArrayList<String> specs = new ArrayList<String>();
for(Doctor d:docs){
	String speciality  = d.getSpeciality();
	if(!specs.contains(speciality))
		{ %>
		<td><a style=color:black; href="bookAppointmentServlet?action=search&speciality=<%=speciality %>"
		 name="speciality" value="<%=speciality%>"><%=speciality%></a></td>
		<%specs.add(speciality); }
	}
}
if(request.getAttribute("message")!=null)%> <p> <%=request.getAttribute("message")%>
</tr></table>
</body>
</html>