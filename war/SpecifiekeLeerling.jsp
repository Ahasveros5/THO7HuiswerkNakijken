	<%@ include file="header.jsp" %>	
	
	<div id ="hwbox">
	<%@ page import="huiswerknakijken.hu.Domain.Person" %>
	<%@ page import="java.util.ArrayList" %>
		</div>
	
	 <%  Person p = (Person)session.getAttribute("selectedStudent");
		if(p!=null){
			out.println("<form action=''>");
			out.println("Voornaam: <input type='textfield' name='voornaam' value='"+ p.getFirstName() +"'>  </input></br>");
			out.println("Achternaam: <input type='textfield' name='achternaam' value='"+ p.getLastName() +"'>  </input></br>");
			out.println("Email: <input type='textfield' name='email' value='"+ p.getEmail() +"'>  </input></br>");
			out.println("</form");
		}
	%>
