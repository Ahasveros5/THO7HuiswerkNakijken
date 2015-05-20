<!DOCTYPE html>
<html>
	<head>
		<title>Vraag aanmaken</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
	</head>
	
	<body>
	<%@ include file="header.jsp" %>	
	<%@ page import="java.util.ArrayList" %>
	<%@ page import="huiswerknakijken.hu.Domain.Person" %>
	<%@ page import="huiswerknakijken.hu.Domain.Student" %>
	<%@ page import="huiswerknakijken.hu.Domain.Class" %>
	<h3>Studenten</h3>
	<div id="LeerlingContainer">
	<%
	ArrayList<Student> students = (ArrayList<Student>) session.getAttribute("Leerlingen");
	if (students!=null){
			for(int i = 0; i< students.size(); i++){
				out.println(""+ students.get(i).getFirstName()+" "+students.get(i).getLastName()+" "+students.get(i).getID()+"<br>");
				
			}	
		}
	%>
	</div>
	<h3>Klassen</h3>
	<div id ="klassenContainer">
	<%
	ArrayList<Class> klassen = (ArrayList<Class>) session.getAttribute("klassen");
	if (klassen!=null){
			for(int i = 0; i< klassen.size(); i++){
				out.println(""+klassen.get(i).getName()+"<br>");
				
			}	
		}
	%>
	</div>
	<button id="show">Klas Aanmaken</button> 
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
</script>

	</body>
</html>