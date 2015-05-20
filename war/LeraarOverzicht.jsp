 <%@ include file="header.jsp" %>
 
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
	<label>Naam:</label>
	<input type = "text" name = "HWName"> <br>
	<label>Deadline:</label>
	<input type="date" name="deadline"/>
	<input type = "time" name="DLTime"/><br>
	
	
	<input class = "button" value = "Huiswerk Aanmaken" type = "submit" name="knop" />
	</form>
	</div>

	</body>
</html>