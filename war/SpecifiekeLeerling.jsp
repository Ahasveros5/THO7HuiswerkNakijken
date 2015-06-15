	<%@ include file="header.jsp" %>	
	
	<%@ page import="huiswerknakijken.hu.Domain.Person" %>
	<%@ page import="huiswerknakijken.hu.Domain.Klass" %>
	<%@ page import="java.util.ArrayList" %>
	
	 <%  Person p = (Person)session.getAttribute("selectedStudent");
		if(p!=null){
			out.println("<form action='GegevensWijzigenServlet.do'>");
			out.println("Voornaam: <input type='textfield' name='voornaam' value='"+ p.getFirstName() +"'>  </input></br>");
			out.println("Achternaam: <input type='textfield' name='achternaam' value='"+ p.getLastName() +"'>  </input></br>");
			out.println("Email: <input type='textfield' name='email' value='"+ p.getEmail() +"'>  </input></br>");
			out.println("Wachtwoord: <input type='textfield' name='wachtwoord' value=''>  </input></br>");
			ArrayList<Klass> klassen = (ArrayList<Klass>) session.getAttribute("klassen");
			out.println("Klas: <select name = 'KlasSelect'>");
			if (klassen!=null){
					for(int i = 0; i< klassen.size(); i++){
						out.println("<option value ='"+klassen.get(i).getName()+"'>"+klassen.get(i).getName()+"</option>");
					}	
				}
			out.println("</select><br/>");
			out.println("<input type='submit' name='Pas aan' value='Aanpassen'> </input>");
			out.println("</form");
		}
	%>
