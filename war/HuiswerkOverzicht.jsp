	<%@ include file="header.jsp" %>	
	
	<div id ="hwbox">
	<%@ page import="huiswerknakijken.hu.Domain.Homework" %>
	<%@ page import="huiswerknakijken.hu.Domain.Question" %>
	<%@ page import="java.util.ArrayList" %>
	<%  Homework hw = (Homework)session.getAttribute("HwObj");
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
	 <input class = "button" value = "Open woord vraag toevoegen" type = "submit" name="knop" />
	 </form>
	 <form action = "MultipleChoiceAanmaken.jsp">
	 <input class = "button" value = "Open vraag toevoegen(NYI)" type = "submit" name="knop" />
	 </form>
