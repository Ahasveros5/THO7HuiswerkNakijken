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
 	<%ArrayList hw = (ArrayList)request.getAttribute("Huiswerk");
 	out.println("<div id=\"hwbox\">");
 		if (hw!=null){
 			for(int i = 0; i< hw.size(); i++){
 				out.println(hw.get(i).toString()+"<br>");
 				out.println(hw.size());
 				
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
	<br>
	
	<form action = "LeraarVraagAanmaken.jsp">
	<input value = "Vraag Aanmaken" type="submit" name="knop" />
	</form>
	

	</body>
</html>