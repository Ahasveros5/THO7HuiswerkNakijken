<%@ include file="header.jsp" %>
 
 	<%@ page import="java.util.ArrayList" %>
 	<%@ page import="huiswerknakijken.hu.Domain.Course"  %>
 	<%@ page import="huiswerknakijken.hu.Domain.Klass"  %>
 	<%@ page import="huiswerknakijken.hu.Domain.Person"  %>
 	
 	<h4>Leerlingen die dit vak volgen:</h4><br>
 	<%
	ArrayList<Person> studenten = (ArrayList<Person>) session.getAttribute("studenten");
	if (studenten!=null){
			for(int i = 0; i< studenten.size(); i++){
				out.println(studenten.get(i).getFirstName()+" "+studenten.get(i).getLastName()+"  "+studenten.get(i).getID());
			}	
		}
	else{
		out.println("Er zijn nog geen leerlingen die dit vak volgen");
	}
	%>
 	<form action = "AddClassToCourse.do"method="post">
 	<label>Klas:</label>
 	<%
	ArrayList<Klass> klassen = (ArrayList<Klass>) session.getAttribute("Klassen");
	out.println("<select name = 'VakSelect'>");
	if (klassen!=null){
			for(int i = 0; i< klassen.size(); i++){
				out.println("<option value ='"+klassen.get(i).getName()+"'>"+klassen.get(i).getName()+"</option>");
			}	
		}
	out.println("</select>");
	%>
	<br>
	<input type = "submit" value = "Voeg klas aan vak toe" class = "button"/>
	</form>
	<br />
	