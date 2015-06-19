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
	 <%  Homework hwpp  = (Homework)session.getAttribute("HwObj");
	 	 Course c = hwpp.getCourse();
		 ArrayList<Student> students = c.getStudents();
	 if(hwpp!=null){
		 if(students.size()>0){
			for(int i = 0; i<students.size(); i++){
				
					out.println(students.get(i).toString()+" status:  "+hwpp.getStatus()+ " ");
				if(hwpp.getCijfer() != -1) {
					out.println("Cijfer: " + hwpp.getCijfer());
			}
			}
		 }
				else{
					out.println("Er zijn nog geen studenten die dit vak volgen");
				}		
		}
	
		%>
		</div>
 	<form action = "HuiswerkNakijkenServlet.do">
	 <input class = "button" value = "Nakijken" type = "submit" name="knop" />
	 </form>
	 
	 
