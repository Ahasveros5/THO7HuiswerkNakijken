 <%@ include file="header.jsp" %>
 
 	<%@ page import="java.util.ArrayList" %>
 	<%@ page import="huiswerknakijken.hu.Domain.Homework" %>
 	<%@ page import="huiswerknakijken.hu.Domain.Klass"  %>
 	<%  /*ArrayList<Homework> hw = (ArrayList<Homework>) session.getAttribute("Huiswerk");
 	out.println("<div id=\"hwbox\">");
 		if (hw!=null){
 			for(int i = 0; i< hw.size(); i++){
 				out.println("<a href = 'HuiswerkOverzichtServlet.do?id="+hw.get(i).getID()+"'>"+hw.get(i).toString()+"</a><br>");
 				
 			}	
 		}
 	out.println("</div>");*/
 	  %>
 	  
 	<div class="TableStyle">
		<table>
				<tr>
					<td>Name</td>
					<td>Deadline</td>
					<td>Aantal vragen</td>
				</tr>
		
		<%
		ArrayList<Homework> hw = (ArrayList<Homework>) session.getAttribute("Huiswerk");
			if (hw != null){
				for(Homework h : hw){
					out.println("<tr>");
					out.println("<td><a href = 'HuiswerkOverzichtServlet.do?id="+h.getID()+"'>"+h.getName()+"</a></td>");
					out.println("<td><a href = 'HuiswerkOverzichtServlet.do?id="+h.getID()+"'>"+h.getDeadline()+"</a></td>");
					out.println("<td><a href = 'HuiswerkOverzichtServlet?id="+h.getID()+"'>"+h.getNumberQuestions()+"</a></td>");
					out.println("</tr>");
				}
			}
		%>
		</table>
	</div>
 
	<div id = "HW">
	<h3> Huiswerk opgeven</h3>
	<form action = "HuiswerkAanmakenServlet.do"method="post">
	<label>Naam:</label>
	<input type = "text" name = "HWName"> <br><br>
	<label>Deadline:</label>
	<input type="date" name="deadline"/>
	<input type = "time" name="DLTime"/><br><br>
	<label>Voor Klas: </label>
	<%
	ArrayList<Klass> klassen = (ArrayList<Klass>) session.getAttribute("klassen");
	out.println("<select name = 'KlasSelect' size='"+klassen.size()+"'>");
	if (klassen!=null){
			for(int i = 0; i< klassen.size(); i++){
				out.println("<option value ='"+klassen.get(i).getName()+"'>"+klassen.get(i).getName()+"</option>");
			}	
		}
	out.println("</select>");
	%>

	<br />
	
	<input class = "button" value = "Huiswerk Aanmaken" type = "submit" name="knop" />
	</form>
	</div>

	</body>
</html>