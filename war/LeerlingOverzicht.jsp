 <%@ include file="header.jsp" %>
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
 	<%@ page import="java.util.ArrayList" %>
 	<%@ page import="huiswerknakijken.hu.Domain.Homework" %>
 	<h3>Huiswerk overzicht</h3>
 	<%  ArrayList<Homework> hw = (ArrayList<Homework>) session.getAttribute("Huiswerk");
 	out.println("<div id=\"hwbox\">");
 		if (hw!=null){
 			for(int i = 0; i< hw.size(); i++){
 				out.println("<a href = 'HuiswerkOverzichtServlet.do?id="+hw.get(i).getID()+"'>"+hw.get(i).toString()+"</a><br>");
 				
 			}	
 		}
 	out.println("</div>");
 	  %>
 	  <table class="table table-hover">
 	  <thead>
			<tr>
				<td>Naam</td>
				<td>Deadline</td>
				<td>Leraar</td>
			</tr>
		</thead>
 	  <tbody>
 	  <tr class="clickableRow" href="www.google.com">
 	  
 	  	<td href="">1</td>
 	  	<td>2</td>
 	  	<td>3</td>
 	  
 	  </tr>
 	  	
 	  
 	  </tbody>
 	  </table>

	</body>
</html>