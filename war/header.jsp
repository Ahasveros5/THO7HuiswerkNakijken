<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ page import="huiswerknakijken.hu.Domain.Person"%>
<%@ page import="huiswerknakijken.hu.Domain.Person.UserRole"%>
<link rel="stylesheet" type="text/css" href="/THO7HuiswerkNakijken/css/style.css">


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HU automatisch huiswerk nakijksysteem</title>
</head>
<body>
		<div class="headerlist headerborder huImage">
		<% 
		if(request.getSession(false) == null){
			RequestDispatcher rd = request.getRequestDispatcher("loginpage.jsp?id=1");
			rd.forward(request, response);
		}
			
		if(session.getAttribute("user") != null){ 
		Person p = (Person) session.getAttribute("user");
		out.println("Welkom: " + p.getFirstName() +" " +  p.getLastName());
		
		}else{
		out.println("Welkom");} %>
		<% if(session.getAttribute("user") == null){ %>
				<ul>
					<li><a href="loginpage.jsp">Login</a></li>
					<li><a href="LeerlingRegistreren.jsp" class="regbutton">Registreer leerling</a></li>
				</ul>
		<% }else {
			Person p = (Person)session.getAttribute("user");
		
			
			if(p.getRole() == UserRole.Student) { %>
				<ul>
					<li><a href="LeerlingOverzicht.jsp" class="regbutton">Overzicht huiswerk</a></li>
					<li><a href="LogoutServlet.do">Uitloggen</a></li>
				</ul>
		<%}	else if(p.getRole() == UserRole.Teacher){%>
				<ul>
				<li><a href="VakkenOverzichtServlet.do" class="regbutton">Overzicht Vakken</a>
				<li><a href="LeraarOverzichtServlet.do" class="regbutton">Overzicht Huiswerk</a></li>
				<li><a href="OverzichtLeerlingenServlet.do" class="regbutton">Overzicht Leerlingen</a></li>
				<li><a href="LeerlingImporteren.jsp" class="regbutton">Leerlingen importeren</a></li>
				<li><a href="LeraarRegistreren.jsp" class="regbutton">Registreer leraar</a></li>
				<li><a href="LogoutServlet.do">Uitloggen</a></li>
			</ul>

		<%} else{%>
			<ul>
				<li><a href="404.jsp">--</a></li>
				<li><a href="404.jsp" class="regbutton">---</a></li>
				<li><a href="LogoutServlet.do">Uitloggen</a></li>
			</ul>
		<%}}%>
		</div>
	<div class="container">
	<% Object msgs = request.getAttribute("msgs");
	if (msgs != null) {
		out.println("<div id=\"messagebox\">");
		out.println(msgs); 
		out.println("</div>");
	} 
	%>
