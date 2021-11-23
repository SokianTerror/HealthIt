<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="styling/navstyle.css"> 
<link href="https://fonts.googleapis.com/css?family=Alfa+Slab+One|Open+Sans" rel="stylesheet">
</head>
<header>
<h1 class="ep">Health <span class="epspan">It</span></h1>
<nav>
	<ul class="ul">
		<li> <a href="index.jsp">Home</a><li>
		<% if (session.getAttribute("uname") == null){%><li> <a href="login.jsp">Login</a><li> <%} else{%>
		<li> <a>Welcome <%=session.getAttribute("uname")%></a><li>
		<li> <a href="logoutServlet">Logout</a><li><%} %><!--  If a uname attribute has been given then type welcome uname  -->
		<li> <a href="About.jsp">About</a><li> <!-- otherwise print login --> 
	</a>
		
	</ul>
</nav>
</header>
<body>
</body>
</html>