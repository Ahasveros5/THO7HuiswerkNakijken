<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ page import="huiswerknakijken.hu.Domain.Person"%>
<%@ page import="huiswerknakijken.hu.Domain.Person.UserRole"%>
<link rel="stylesheet" type="text/css" href="/THO7HuiswerkNakijken/css/style.css">
<link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HU automatisch huiswerk nakijksysteem</title>
</head>
<body>
		<div class="headerlist headerborder">
		<% if(session.getAttribute("user") != null){ 
		Person p = (Person) session.getAttribute("user");
		System.out.println("hallo: " + p.getFirstName());
		out.println("Welkom: " + p.getFirstName() +" " +  p.getLastName());
		
		}else{
		out.println("Welkom");} %>
		<% if(session.getAttribute("user") == null){ %>
				<ul>
					<li><a href="loginpage.jsp">Login</a></li>
					<li><a href="LeerlingRegistreren.jsp" class="regbutton">Registreer leerling</a></li>
					<li><a href="LeraarRegistreren.jsp" class="regbutton">Registreer leraar</a></li>
				</ul>
		<% }else {
			Person p = (Person)session.getAttribute("user");
		
			
			if(p.getRole() == UserRole.Student) { %>
				<ul>
					<li><a href="404.jsp">Huiswerk maken</a></li>
					<li><a href="404.jsp" class="regbutton">Overzicht huiswerk</a></li>
					<li><a href="LogoutServlet.do">Uitloggen</a></li>
				</ul>
		<%}	else if(p.getRole() == UserRole.Teacher){%>
				<ul>
				<li><a href="404.jsp">Huiswerk aanmaken</a></li>
				<li><a href="404.jsp" class="regbutton">Overzicht huiswerk</a></li>
				<li><a href="404.jsp" class="regbutton">Overzicht leerlingen</a></li>
				<li><a href="LogoutServlet.do">Uitloggen</a></li>
			</ul>

		<%} else{%>
			<ul>
				<li><a href="loginpage.jsp">Huiswerk12 maken</a></li>
				<li><a href="LeerlingRegistreren.jsp" class="regbutton">Overzicht12 huiswerk</a></li>
				<li><a href="LogoutServlet.do">Uitloggen</a></li>
			</ul>
		<%}}%>
		</div>
	<div class="container">