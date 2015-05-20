	<%@ include file="header.jsp" %>	
	
	<div id ="hwbox">
	<%@ page import="huiswerknakijken.hu.Domain.Homework" %>
	<%@ page import="huiswerknakijken.hu.Domain.Question" %>
	<%@ page import="java.util.ArrayList" %>
	<%  Homework hw = (Homework)session.getAttribute("HwObj");

		out.println("<h2>"+hw.getName() +" maken</h2>");
	
		%>
		</div>
	<form action = "VraagMaken.jsp" method="post">
		<input type="hidden" value="1" />
	 	<input type="submit" value="Begin met huiswerk" name="Begin met huiswerk" />
	</form>
