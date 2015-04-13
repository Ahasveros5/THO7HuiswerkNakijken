<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ page import="huiswerknakijken.hu.Domain.Person"%>
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
		<% }else { %>
				<ul>
					<li><a href="loginpage.jsp">Huiswerk maken</a></li>
					<li><a href="LeerlingRegistreren.jsp" class="regbutton">Overzicht huiswerk</a></li>
					<li><input class="button" action = "LogoutServlet.do" type="submit" name="knop" value="Uitloggen" />
				</ul>
		<%} %>
		</div>
	<div class="container">