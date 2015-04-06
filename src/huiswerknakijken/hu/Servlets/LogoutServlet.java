package huiswerknakijken.hu.Servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		String userName = "";
		
		for (Cookie cookie : req.getCookies()) {
			if (cookie.getName().equals("userName")) { 
				userName = cookie.getValue();
				cookie.setMaxAge(0);
				break;
			}
		}
		
		File loggedUserFile = new File("/usr/local/Cellar/tomcat/7.0.39/libexec/webapps/LoginSystem/loggedUsers.txt");
		File tempFile = new File("myTempFile.txt");

		BufferedReader reader = new BufferedReader(new FileReader(loggedUserFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		String lineToRemove = userName;
		String currentLine;

		while((currentLine = reader.readLine()) != null) {
		    String trimmedLine = currentLine.trim();
		    if(trimmedLine.equals(lineToRemove)) continue;
		    writer.write(currentLine);
		}

		boolean successful = tempFile.renameTo(loggedUserFile);
		
		RequestDispatcher rd = null;

		rd = req.getRequestDispatcher("login.jsp");
		rd.forward(req, resp);
	}
}
