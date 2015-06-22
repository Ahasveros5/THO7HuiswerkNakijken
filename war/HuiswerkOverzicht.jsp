	<%@ include file="header.jsp" %>	
	
	<div id ="hwbox">
	<%@ page import="huiswerknakijken.hu.Domain.Homework" %>
	<%@ page import="huiswerknakijken.hu.Domain.Question" %>
	<%@ page import="huiswerknakijken.hu.Domain.Person" %>
	<%@ page import="huiswerknakijken.hu.Domain.Student" %>
	<%@ page import="huiswerknakijken.hu.Domain.Course" %>
	<%@ page import="java.util.ArrayList" %>
	<%  Homework hw = ((Homework)session.getAttribute("HwObj"));
		ArrayList<Question> q =(ArrayList<Question>)session.getAttribute("questObj");

		if(hw!=null){
		out.println(hw.toString()+" Vak: "+hw.getCourse().getName()+"<br/>");
		}
		if(q!=null){
		for(int i = 0; i < q.size(); i++){
		out.println(q.get(i).getDescription());
		}		
		}else{
			out.println("Er zijn nog geen vragen");
	}
		%>
		</div>
	<form action = "MultipleChoiceAanmaken.jsp">
	 <input class = "button" value = "Meerkeuze vraag toevoegen" type = "submit" name="knop" />
	 </form>
	 <form action = "OpenWoordAanmaken.jsp">
	 <input class = "button" value = "Open woord vraag toevoegen(NYI)" type = "submit" name="knop" />
	 </form>
	 <form action = "OpenVraagAanmaken.jsp">
	 <input class = "button" value = "Open vraag toevoegen" type = "submit" name="knop" />
	 </form>
	 <div id = "studentBox">
	 <h3>Huiswerk status leerlingen</h3>
	 <div class="TableStyle">
		<table>
			<tr>
				<td>Naam</td>
				<td>Status</td>
				<td>Cijfer</td>
			</tr>
	 <%  ArrayList<Homework> hwpp  = (ArrayList<Homework>)session.getAttribute("Hwpp");
	 	 
		 
	 if(hwpp!=null){
		 Course c = hw.getCourse();
		 ArrayList<Student> students = c.getStudents();
		 if(students.size()>0){
				for(int i = 0; i<students.size(); i++){
					Homework h = hwpp.get(i);
					Person p = students.get(i);
					out.println("<tr>");
					out.println("<td><a href = 'SpecifiekeLeerlingServlet.do?id="+p.getID()+"'>"+p.toString()+"</a></td>");
					out.println("<td>"+h.getStatus()+"</td>");
					if(h.getCijfer() == -1)
						out.println("<td>-</td>");
					else
						out.println("<td>"+h.getCijfer()+"</td>");
					out.println("</tr>");
				}
			 	
		 }else{
				out.println("Er zijn nog geen studenten die dit vak volgen");
			}
					
		}
	
		%>
		</table>
		</div>
		</div>
 	<form action = "HuiswerkNakijkenServlet.do">
	 <input class = "button" value = "Nakijken" type = "submit" name="knop" />
	 </form>
	 
	 
