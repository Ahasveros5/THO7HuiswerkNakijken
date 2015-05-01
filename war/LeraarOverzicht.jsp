<!DOCTYPE html>
<html>
	<head>
		<title>Vraag aanmaken</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
	</head>
	
	<body>
	<%@ include file="header.jsp" %>	
	<% Object msgs = request.getAttribute("msgs");
	if (msgs != null) {
		out.println("<div id=\"messagebox\">");
		out.println(msgs); 
		out.println("</div>");
	} 
	%>
 	<%@ page import="java.util.ArrayList" %>
 	<%@ page import="huiswerknakijken.hu.Domain.Homework" %>
 	<%  ArrayList<Homework> hw = (ArrayList<Homework>) session.getAttribute("Huiswerk");
 	out.println("<div id=\"hwbox\">");
 		if (hw!=null){
 			for(int i = 0; i< hw.size(); i++){
 				out.println("<a href = 'HuiswerkOverzichtServlet.do?id="+hw.get(i).getID()+"'>"+hw.get(i).toString()+"</a><br>");
 				
 			}	
 		}
 	out.println("</div>");
 	  %>
 
	<div id = "HW">
	<h3> Huiswerk aanmaken</h3>
	<form action = "HuiswerkAanmakenServlet.do"method="post">
	<label>naam:</label>
	<input type = "text" name = "HWName"> 
	<label>deadline:</label>
	<input type="date" name="deadline"/>
	<input type = "time" name="DLTime"/><br>
	
	
	<input class = "button" value = "Huiswerk Aanmaken" type = "submit" name="knop" />
	</form>
	</div>

	</body>
</html>