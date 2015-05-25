	<%@ include file="header.jsp" %>	
	
	<div id ="hwbox">
	<%@ page import="huiswerknakijken.hu.Domain.Answer" %>
	<%@ page import="huiswerknakijken.hu.Domain.Question" %>
	<%@ page import="java.util.ArrayList" %>
	<%  ArrayList<Question> qs = (ArrayList<Question>)session.getAttribute("QuestObj");
	if(qs == null){
		out.println("Geen vragenx2");
	}
		Question cur = null;
		int id = Integer.parseInt(request.getParameter("id"));
		for (Question q : qs){
			if (q.getNumber() ==id){
				cur = q;
				
				break;
			}
		}
		if (cur != null){
			if(cur.getName() != null)
				out.println("<h3>"+cur.getName() +"</h3>");
			out.println("<h2>"+cur.getDescription() +"</h2>");
			ArrayList<Answer> ans = cur.getAnswers();
			if(cur.getAnswers().size() == 0){
				out.println("<h3>Geen antwoorden</h3>");
			}
			int i = 0;
			String[] str = {"A","B","C","D","E","F","G","H","J"};
			out.println("<form action='VraagMakenServlet.do'<br>");
			for(Answer a : ans){
				out.println(str[i] +": <input type='radio' name='answer' value='"+ a.getID()+"'> " + a.getAnswer() +"<br>");
				i++;
			}
			out.println("<input type='hidden' value='"+(id+1)+"' name='id' />");
			out.println("<input type='hidden' value='"+cur.getID()+"' name='qid' />");
			out.println("<input value = 'Volgende vraag' type = 'submit' name='knop' />");
			out.println("</form>");
		} else {
			out.println("<h3>Geen vragen</h3>");
		}
	
		%>
		</div>