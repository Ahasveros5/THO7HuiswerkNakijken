package nl.hu.ThemaOpdracht4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");

		String userEmail = req.getParameter("email_login");
		String password = req.getParameter("ww_login");
		
		RequestDispatcher rd = null;
		HttpSession session = null;
		if(userEmail.isEmpty() || password.isEmpty()) {
			req.setAttribute("msgs", "Vul alle velden in.");
			rd = req.getRequestDispatcher("login.jsp");
		} else {
				if(userExists(userEmail)) {
					String savedPassword = getUserPassword(userEmail);
					if(password.equals(savedPassword)) {
						loginUser(userEmail, resp);
						session.setAttribute("userEmail",userEmail );
						rd = req.getRequestDispatcher("loggedin.jsp");
					} else {
						req.setAttribute("msgs", "Wachtwoord komt niet overeen");
						rd = req.getRequestDispatcher("login.jsp");
					}
				} else {
					req.setAttribute("msgs", "Gebruiker bestaat niet");
					rd = req.getRequestDispatcher("loggedin.jsp");
				}
		}
		
		if(rd != null) {
			rd.forward(req, resp);
		}
	}
	
	protected void loginUser(String userName, HttpServletResponse resp) {
		Cookie cookie = new Cookie("userName", userName);
		resp.addCookie(cookie);
		
		File userFile = new File("/usr/local/Cellar/tomcat/7.0.39/libexec/webapps/LoginSystem/loggedUsers.txt");
		try {
			FileWriter writer = new FileWriter(userFile, true);
			writer.write(
					userName + "\n");
			writer.close();
			
			System.out.println("User logged in");
		} catch(IOException e) {
			System.out.println("IOException");
		}		
	}
	
	protected String getUserPassword(String userName) {
		File userFile = new File("/usr/local/Cellar/tomcat/7.0.39/libexec/webapps/LoginSystem/registeredUsers.txt");
		if(userFile.exists() || userFile.isFile()) {
			try { 
				FileReader reader = new FileReader(userFile);
				BufferedReader buffer = new BufferedReader(reader);
				
				while (true) {
					String line = buffer.readLine();
					if (line == null) {
						buffer.close();
						break;
					}
					
					String userData[] = line.split("; ");
					if(userData[0].equals(userName)) {
						buffer.close();
						return userData[2];
					}
				}
			} catch(Exception e) {
				System.out.println("Foutje");
			}
		}
		
		return "";
	}
	
	protected boolean userExists(String userName) {
		File userFile = new File("/usr/local/Cellar/tomcat/7.0.39/libexec/webapps/LoginSystem/registeredUsers.txt");
		if(!userFile.exists() || !userFile.isFile()) {
			return false;
		} else {
			try { 
				FileReader reader = new FileReader(userFile);
				BufferedReader buffer = new BufferedReader(reader);
				
				while (true) {
					String line = buffer.readLine();
					if (line == null) {
						buffer.close();
						return false;
					}
					
					String userData[] = line.split("; ");
					if(userData[0].equals(userName)) {
						buffer.close();
						return true;
					}
				}
			} catch(Exception e) {
				System.out.println("Foutje");
				return true;
			}
		}
	}
}
