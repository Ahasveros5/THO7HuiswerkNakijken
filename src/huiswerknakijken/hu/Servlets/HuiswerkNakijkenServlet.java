package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.AnswerDAO;
import huiswerknakijken.hu.Domain.Homework;
import huiswerknakijken.hu.Domain.Homework.Status;
import huiswerknakijken.hu.Domain.Question;
import huiswerknakijken.hu.Util.CheckAnswers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HuiswerkNakijkenServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		RequestDispatcher rd = null;
		HttpSession session = req.getSession();
		ArrayList<Homework> hws = (ArrayList<Homework>) session.getAttribute("HwObj");
		int hwID = Integer.parseInt((String)session.getAttribute("hwID"));
		AnswerDAO adao = new AnswerDAO();
		int correct = 0;
		for(Homework h : hws){
			if(h.getStatus() != Status.Af)
				continue;
			for(Question q : h.getQuestions()){
				if(CheckAnswers.HasGivenCorrectAnswer(q, adao.retrieveGivenAnswer(q, h.getStudent(), 1)))
					correct++;
			}
			h.cijfer = correct/h.getNumberQuestions()*9+1;
			System.out.println("cijfer: " + h.cijfer);
		}
		session.setAttribute("HwObj", hws);
		
		rd = req.getRequestDispatcher("HuiswerkMaken.jsp");
		
		if(rd!= null){
			rd.forward(req, resp);
		}
	}
}
