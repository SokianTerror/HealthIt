<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style> 
*{
	box-sizing: border-box;
}
.child{
	border: 2px solid black;
}
@media (min-width: 1000px){
	.parent{
		display:flex;
	}

}
</style>
<meta charset="UTF-8">
<title>Admin Index</title>
<%@ include file="styling/navbar.jsp" %>  
</head>
<body>
<%
response.setHeader("Cache-Control","no-cache"); //index page for everyone
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
if(session.getAttribute("uname")==null || !session.getAttribute("usertype").equals("admin")){
	response.sendRedirect("redirectionjsp.jsp");
}%>
<h1> Welcome <%=session.getAttribute("uname") %> </h1>
<section class="parent">
<form class="child" method=post action=addDoctorinoServlet>
	<p> Doctor Amka:</p>
	<input type=number min=0 value=000 name=doc_amka required> 
	<p> Username:</p>
	<input type=text value=username name =username required> 
	<p> Password:</p>
	<input type=text value=password name =password required> 
	<p> name:</p>
	<input type=text value=name name =name required> 
	<p> surname:</p>
	<input type=text value=surname name =surname required> 
	<p> speciality:</p>
	<input type=text value=speciality name=speciality required> <br>
	<input type=submit value="Add Doctor">
</form>
<form class="child" method=post action=addPatientoServlet>
	<p> Patient Amka:</p>
	<input type=number min=0 value=000 name=doc_amka required> 
	<p> Username:</p>
	<input type=text value=username name =username required> 
	<p> Password:</p>
	<input type=text value=password name =password required> 
	<p> name:</p>
	<input type=text value=name name =name required> 
	<p> surname:</p>
	<input type=text value=surname name =surname required> <br>
	<input type=submit value="Add Patient">
</form>
<form class="child" method=post action=deleteDoctorinoServlet>
	<p> Doctor id:</p>
	<input type=number min=0 value=000 name=deldoc required> 
	<p> Doctor id:</p>
	<input type=text value=username name=docuname required> 
	<br>
	<input type=submit value="Delete Doctor">
</form>
</section>
</body>
</html>