 <%@ include file="header.jsp" %>
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
 	<%@ page import="java.util.ArrayList" %>
 	<%@ page import="huiswerknakijken.hu.Domain.Homework" %>
 	<h3>Huiswerk overzicht</h3>
 	<table border="1">
	 	<thead>
			<tr>
				<td>Naam</td>
				<td>Deadline</td>
				<td>Leraar</td>
			</tr>
		</thead>
	<tbody>
 	<%  ArrayList<Homework> hw = (ArrayList<Homework>) session.getAttribute("Huiswerk");
 	out.println("<div id=\"hwbox\">");
 		if (hw!=null){
 			for(int i = 0; i< hw.size(); i++){
 				Homework h = hw.get(i);
 				out.println("<tr>");
 				out.println("<td class='border'><a href = 'HuiswerkMakenServlet.do?id="+h.getID()+"'>"+h.getName()+"</a></td>>");
 				out.println("<td class='border'><a href = 'HuiswerkMakenServlet.do?id="+h.getID()+"'>"+h.getDeadline()+"</a></td>>");
 				out.println("<td class='border'><a href = 'HuiswerkMakenServlet.do?id="+h.getID()+"'>"+h.getTeacher().getFirstName()+ " "+h.getTeacher().getLastName() +"</a></td>>");
 				out.println("</tr>");
 				
 			}	
 		}
 	out.println("</div>");
 	  %>
 	  </tbody>
 	  </table>

	</body>
</html>