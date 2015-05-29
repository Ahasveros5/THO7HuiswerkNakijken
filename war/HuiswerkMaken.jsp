	<%@ include file="header.jsp" %>	
	
	<div id ="hwbox">
	<%@ page import="huiswerknakijken.hu.Domain.Homework" %>
	<%@ page import="huiswerknakijken.hu.Domain.Homework.Status" %>
	<%@ page import="huiswerknakijken.hu.Domain.Question" %>
	<%@ page import="java.util.ArrayList" %>
	<%  Homework hw = (Homework)session.getAttribute("HwObj");

		out.println("<h2>"+hw.getName() +" maken</h2>");
		out.println("</div>");
		if(hw.getStatus() == Status.Nieuw){
			out.println("<form action = 'VraagMaken.jsp' method='post'>");
			out.println("<input type='hidden' value='1' name='id' />");
			out.println("<input type='submit' value='Begin met huiswerk' name='Begin met huiswerk' />");
			out.println("</form>");
		} else if(hw.getStatus() == Status.Begonnen){
			out.println("<form action = 'VraagMaken.jsp' method='post'>");
			out.println("<input type='hidden' value='" + hw.getCurrentQuestion() + "' name='id' />");
			out.println("<input type='submit' value='Ga verder met huiswerk' name='Ga verder met huiswerk' />");
			out.println("</form>");
		} else{
			out.println("<form action = 'VraagMaken.jsp' method='post'>");
			out.println("<input type='hidden' value='1' name='id' />");
			out.println("<input type='submit' value='Kijk huiswerk na' name='Kijk huiswerk na' />");
			out.println("</form>");
		}
%>