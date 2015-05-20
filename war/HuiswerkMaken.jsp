	<%@ include file="header.jsp" %>	
	
	<div id ="hwbox">
	<%@ page import="huiswerknakijken.hu.Domain.Homework" %>
	<%@ page import="huiswerknakijken.hu.Domain.Question" %>
	<%@ page import="java.util.ArrayList" %>
	<%  Homework hw = (Homework)session.getAttribute("HwObj");

		out.println("<h2>"+hw.getName() +" maken</h2>");
	
		%>
		</div>
	<form action = "VraagMaken.jsp?id=1">
	 <input class = "button" value = "Begin aan het huiswerk" type = "submit" name="knop" />
	 </form>
