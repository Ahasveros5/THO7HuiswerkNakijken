 <%@ include file="header.jsp" %>
 	<%@ page import="java.util.ArrayList" %>
 	<%@ page import="huiswerknakijken.hu.Domain.Homework" %>
 	<h3>Huiswerk overzicht</h3>
 	<div class="TableStyle">
 	<table>
 	<caption>Huiswerk</caption>
			<tr>
				<td>Vak</td>
				<td>Naam</td>
				<td>Deadline</td>
				<td>Leraar</td>
			</tr>
 	<%  ArrayList<Homework> hw = (ArrayList<Homework>) session.getAttribute("Huiswerk");
 		ArrayList<Homework> hwaf = (ArrayList<Homework>) session.getAttribute("HuiswerkAf");
 		if (hw!=null && hw.size()>0){
 			for(int i = 0; i< hw.size(); i++){
 				Homework h = hw.get(i);
 				out.println("<tr>");
 				out.println("<td class='border'><a href = 'HuiswerkMakenServlet.do?id="+h.getID()+"'>"+h.getCourse().getName()+"</a></td>");
 				out.println("<td class='border'><a href = 'HuiswerkMakenServlet.do?id="+h.getID()+"'>"+h.getName()+"</a></td>");
 				out.println("<td class='border'><a href = 'HuiswerkMakenServlet.do?id="+h.getID()+"'>"+h.getDeadline()+"</a></td>");
 				out.println("<td class='border'><a href = 'HuiswerkMakenServlet.do?id="+h.getID()+"'>"+h.getTeacher().getFirstName()+ " "+h.getTeacher().getLastName() +"</a></td>");
 				out.println("</tr>");
 				
 			}	
 		}
 		out.println("</table></br>");
 		
 		out.println("<table>");
 		out.println("<caption>Huiswerk af</caption>");
 		out.println("<tr>");
			out.println("<td>Vak</td>");
			out.println("<td>Naam</td>");
			out.println("<td>Deadline</td>");
			out.println("<td>Leraar</td>");
 		out.println("</tr>");
 		if (hwaf!=null && hwaf.size()>0){
 			for(int i = 0; i< hwaf.size(); i++){
 				Homework h = hwaf.get(i);
 				out.println("<tr>");
 				out.println("<td class='border'><a href = 'HuiswerkMakenServlet.do?id="+h.getID()+"'>"+h.getCourse().getName()+"</a></td>");
 				out.println("<td class='border'><a href = 'HuiswerkMakenServlet.do?id="+h.getID()+"'>"+h.getName()+"</a></td>");
 				out.println("<td class='border'><a href = 'HuiswerkMakenServlet.do?id="+h.getID()+"'>"+h.getDeadline()+"</a></td>");
 				out.println("<td class='border'><a href = 'HuiswerkMakenServlet.do?id="+h.getID()+"'>"+h.getTeacher().getFirstName()+ " "+h.getTeacher().getLastName() +"</a></td>");
 				out.println("</tr>");
 				
 			}	
 		}
 	  %>
 	  </table>
	</div>
	</body>
</html>