	<%@ include file="header.jsp" %>	
	
	<div id ="hwbox">
	<%@ page import="huiswerknakijken.hu.Domain.Homework" %>
	<%@ page import="huiswerknakijken.hu.Domain.Question" %>
	<%@ page import="huiswerknakijken.hu.Domain.Person" %>
	<%@ page import="java.util.ArrayList" %>
	<%  Homework hw = ((ArrayList<Homework>)session.getAttribute("HwObj")).get(0);
		ArrayList<Question> q =(ArrayList<Question>)session.getAttribute("questObj");

		if(hw!=null){
		out.println(hw.toString()+"<br/>");
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
	 <%  ArrayList<Homework> hwpp = (ArrayList<Homework>)session.getAttribute("HwObj");
		if(hwpp!=null){
			for(int i = 0; i<hwpp.size(); i++){
				
			out.println(hwpp.get(i).getStudent().toString()+" status:  "+hwpp.get(i).getStatus()+ " ");
			if(hwpp.get(i).getCijfer() != -1) {
				out.println("Cijfer: " + hwpp.get(i).getCijfer());
			}
		}
	}
		%>
		</div>
 	<form action = "HuiswerkNakijkenServlet.do">
	 <input class = "button" value = "Nakijken" type = "submit" name="knop" />
	 </form>
