	<%@ include file="header.jsp" %>	
	<%@ page import="java.util.ArrayList" %>
	<%@ page import="huiswerknakijken.hu.Domain.Person" %>
	<%@ page import="huiswerknakijken.hu.Domain.Student" %>
	<%@ page import="huiswerknakijken.hu.Domain.Klass" %>
	
	<div>
	<form action = "SearchLeerlingenServlet.do">
	<%
		String keyword = (String)session.getAttribute("Keyword");
		if(keyword != null)
			out.println("<label size='250'>Gezocht naar: " + keyword + " </label><br/>");
	%>
	<input class="searchfield" type="text" name="searchfield"/>
	<input class="searchbutton" type="submit" name="search" value="search"/>
	</form>
	</div>
	
	<div class="TableStyle">
	<table>
			<tr>
				<td>ID</td>
				<td>Naam</td>
				<td>Klas</td>
			</tr>
	
	<%
		ArrayList<Student> leerlingen = (ArrayList<Student>)session.getAttribute("GevondenLeerlingen");
		ArrayList<Klass> klassen = (ArrayList<Klass>)session.getAttribute("GevondenKlassen");
		if (leerlingen != null){
			for(int i = 0; i<leerlingen.size(); i++){
				Person p = leerlingen.get(i);
				out.println("<tr>");
				out.println("<td><a href = 'SpecifiekeLeerlingServlet.do?id="+p.getID()+"'>"+p.getID()+"</a></td>");
				out.println("<td><a href = 'SpecifiekeLeerlingServlet.do?id="+p.getID()+"'>"+p.getFirstName()+ " " +p.getLastName()+"</a></td>");
				out.println("<td><a href = 'SpecifiekeLeerlingServlet.do?id="+p.getID()+"'>"+klassen.get(i).getName()+"</a></td>");
				out.println("</tr>");
			}
		}
	%>
	</table>
	</div>
	
	<!--<div id = "wrapper">
	<h3>Studenten</h3>
	<div id="LeerlingContainer">
	<%
	/*ArrayList<Student> students = (ArrayList<Student>) session.getAttribute("Leerlingen");
	out.println("<select size='"+students.size()+"' multiple>");
	if (students!=null){
			for(int i = 0; i< students.size(); i++){
				out.println("<option value ='"+students.get(i).getID() +"'>" +students.get(i).getFirstName()+" "+students.get(i).getLastName()+" "+students.get(i).getID()+"</option>");
				
			}	
		}out.println("</select>");*/
	%>
	</div>
	<div id ="addButton">
	<button>Voeg toe aan klas</button>
	</div>
	<div id ="KlassenContainer">
	<h3>Klassen</h3>
	<%
	/*ArrayList<Klass> klassen = (ArrayList<Klass>) session.getAttribute("klassen");
	out.println("<select size='"+klassen.size()+"'>");
	if (klassen!=null){
			for(int i = 0; i< klassen.size(); i++){
				out.println("<option value ='"+klassen.get(i).getName()+"'>"+klassen.get(i).getName()+"</option>");
				
			}	
		}
	out.println("</select>");*/
	%>
	<br />
	<button id="show">Klas Aanmaken</button> 
	</div>
</div>
	
<dialog id="window">  
    <h3>Klas Aanmaken</h3>  
    <p>Klasnaam: </p>  
    <form action="KlasMakenServlet.do" method="post">
    <input type = "text" name = "Klasnaam" size="20"/> 
  	<input class="button" type="submit" name="knop" value="Ok" />
	</form>		
    <button id="exit">Cancel
</dialog>  
<script>
(function() {  
    var dialog = document.getElementById('window');  
    document.getElementById('show').onclick = function() {  
        dialog.show();  
    };  
    document.getElementById('exit').onclick = function() {  
        dialog.close();  
    };  
})();
</script>-->