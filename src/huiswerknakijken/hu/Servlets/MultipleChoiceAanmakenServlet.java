package huiswerknakijken.hu.Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MultipleChoiceAanmakenServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		ArrayList<String>antwoorden = new ArrayList<String>();
		// String antweurden = req.getParameter("mogelijkeAntwoorden[]");
		String vraag = req.getParameter("vraagMultipleChoice");

		int i = 1;
		while(true){
			String antwoord = req.getParameter("Antwoord"+i);		
			i++;
			if(antwoord == null || "".equals(antwoord)){
				break;
			}
			antwoorden.add(antwoord);
		}
		
		System.out.println("Antwoorden: "+antwoorden.toString());
		
}
}
