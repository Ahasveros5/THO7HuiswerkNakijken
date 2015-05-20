	<%@ include file="header.jsp" %>	
	
	<div id ="hwbox">
	<%@ page import="huiswerknakijken.hu.Domain.Answer" %>
	<%@ page import="huiswerknakijken.hu.Domain.Question" %>
	<%@ page import="java.util.ArrayList" %>
	<%  ArrayList<Question> qs = (ArrayList<Question>)session.getAttribute("QuestObj");
		Question cur = null;
		int id = (int)session.getAttribute("id");
		for (Question q : qs){
			if (q.getNumber() ==id){
				cur = q;
				break;
			}
		}
		out.println("<h3>"+cur.getName() +"</h3>");
		out.println("<h2>"+cur.getDescription() +"</h2>");
		ArrayList<Answer> ans = cur.getAnswers();
		for(Answer a : ans){
			out.println(""+a.getAnswer() +"");
		}
	
		%>
		</div>
	<form action = "VraagMaken.jsp?id=1">
	 <input class = "button" value = "Begin aan het huiswerk" type = "submit" name="knop" />
	 </form>
