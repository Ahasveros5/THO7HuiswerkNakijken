 <%@ include file="header.jsp" %>
 
 	<%@ page import="java.util.ArrayList" %>
 	<%@ page import="huiswerknakijken.hu.Domain.Homework" %>
 	<%@ page import="huiswerknakijken.hu.Domain.Course"  %>
	<div id = "HW">
	<h3> Huiswerk opgeven</h3>
	<form action = "HuiswerkAanmakenServlet.do"method="post">
	<label>Naam:</label>
	<input type = "text" name = "HWName"> <br><br>
	<label>Deadline:</label>
	<input type="date" name="deadline"/>
	<input type = "time" name="DLTime"/><br><br>
	<label>Vak: </label>
	<%
	ArrayList<Course> vakken = (ArrayList<Course>) session.getAttribute("Vakken");
	out.println("<select name = 'VakSelect'>");
	if (vakken!=null){
			for(int i = 0; i< vakken.size(); i++){
				out.println("<option value ='"+vakken.get(i).getName()+"'>"+vakken.get(i).getName()+"</option>");
			}	
		}
	out.println("</select>");
	%>

	<br />
	<br>
	
	<input class = "button" value = "Huiswerk Aanmaken" type = "submit" name="knop" />
	</form>
	</div>

	</body>
</html>