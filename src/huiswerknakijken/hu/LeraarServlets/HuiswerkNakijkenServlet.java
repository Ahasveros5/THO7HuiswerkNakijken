package huiswerknakijken.hu.LeraarServlets;

import huiswerknakijken.hu.DAO.AnswerDAO;
import huiswerknakijken.hu.DAO.HomeworkDAO;
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
		AnswerDAO adao = new AnswerDAO();
		HomeworkDAO hdao = new HomeworkDAO();
		double points = 0;
		for(Homework h : hws){
			if(h.getStatus() != Status.Af)
				continue;
			for(Question q : h.getQuestions()){
				points += CheckAnswers.getGivenAnswerPoints(q, h.getStudent());
			}
			h.setCijfer(points/h.getNumberQuestions()*9+1);
			hdao.update(h);
			System.out.println("cijfer: " + h.getCijfer());
		}
		session.setAttribute("HwObj", hws);
		
		rd = req.getRequestDispatcher("HuiswerkOverzicht.jsp");
		
		if(rd!= null){
			rd.forward(req, resp);
		}
	}
}
