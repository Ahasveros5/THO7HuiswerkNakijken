<%@ include file="header.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="huiswerknakijken.hu.Domain.Course" %>
<%@ page import="huiswerknakijken.hu.Domain.Person" %>
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
 <h3>vakken overzicht</h3>
 
 <div id = "vakkenbox">
<%  ArrayList<Course> vakken = (ArrayList<Course>) session.getAttribute("Vakken");

	if (vakken!=null){
			for(int i = 0; i< vakken.size(); i++){
				out.println(vakken.get(i).getName()+"<br>");			
			}			
		}
	else{
			out.println("Nog geen vakken beschikbaar <br> </div>");
		}
	
	%>
 </div>
 <button id="show">Vak Aanmaken</button> 
 <div id ="PopUpWindow">
 <dialog id="window">  
    <h3>Vak Aanmaken</h3>  
    <p>Vak naam: </p>  
    <form action="VakAanmakenServlet.do" method="post">
    <input type = "text" name = "Vaknaam" size="40"/> <br>
    <p> Gegeven door: </p><br>
    <% ArrayList<Person> teachers = (ArrayList<Person>) session.getAttribute("leraren");
    if(teachers !=null){
    	out.println("<select name = 'teacherSelect' size='"+teachers.size()+"' multiple>");
   		 for(int i = 0; i< teachers.size(); i++){
   			out.println("<option value ='"+teachers.get(i).getID() +"'>"+teachers.get(i).getFirstName()+" "+teachers.get(i).getLastName()+"</option>");
   		 }
    }
    %>
  	<input class="button" type="submit" name="knop" value="Ok" />
  	<button id="exit">Cancel</button>
	</form>		
    
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

</div>
</body>
</html>