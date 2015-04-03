package nl.hu.ThemaOpdracht4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class KlantRegistratieServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		String naam = req.getParameter("invoer_naam");
		String achternaam = req.getParameter("invoer_achternaam");
		String geslacht = req.getParameter("invoer_geslacht");

		String straat = req.getParameter("invoer_straat");
		String huisnr = req.getParameter("invoer_huisnr");
		String postc = req.getParameter("invoer_postc");
		String woonpl = req.getParameter("invoer_woonpl");
		String email1 = req.getParameter("invoer_email");
		String email2 = req.getParameter("invoer_emailb");
		String ww1 = req.getParameter("invoer_ww");
		String ww2 = req.getParameter("invoer_wwb");

		RequestDispatcher rd = null;
		ATD a = new ATD("ATD");
		if(
			naam.isEmpty() ||
			achternaam.isEmpty() ||
			geslacht.isEmpty() ||
			straat.isEmpty() ||
			huisnr.isEmpty() ||
			postc.isEmpty() ||
			woonpl.isEmpty()||
			email1.isEmpty()||
			email2.isEmpty()||
			ww1.isEmpty()||
			ww2.isEmpty()) {
			
			req.setAttribute("msgs", "Vul alle velden in.");
			rd = req.getRequestDispatcher("register.jsp");
		} else {
			if(!ww1.equals(ww2) || !email1.equals(email2)) {
				req.setAttribute("msgs", "Velden komen niet overeen");
				rd = req.getRequestDispatcher("register.jsp");
			} else {
				if(userExists(email1)) {
					req.setAttribute("msgs", "Emailadres al in gebruik");
					rd = req.getRequestDispatcher("register.jsp");
				} else {
					addUser(naam, achternaam, geslacht, straat, huisnr, postc, woonpl, email1, email2, ww1,ww2);
					Klant k = new Klant(naam, achternaam, geslacht, straat, huisnr, postc, woonpl, email1, email2, ww1,ww2 );
					a.voegKlantToe(k);
					rd = req.getRequestDispatcher("loginpage.jsp");
				}
			}
		}
		
		if(rd != null) {
			rd.forward(req, resp);
		}
	}
	
	protected void addUser(
			String naam, 
			String achternaam, 
			String geslacht, 
			String straat, 
			String huisnr, 
			String postc,
			String woonpl,
			String email1,
			String email2,
			String ww1,
			String ww2) {
		
		File userFile = new File("/usr/local/Cellar/tomcat/7.0.39/libexec/webapps/LoginSystem/registeredUsers.txt");
		try {
			FileWriter writer = new FileWriter(userFile, true);
			writer.write(
					naam + "; " + 
					achternaam + "; " + 
					geslacht + "; " + 
					straat + "; " + 
					huisnr + "; " + 
					postc + "; " +
					woonpl+"; "+
					email1+"; "+
					email2+"; "+
					ww1+"; "+
					ww2+"; "+"\n");
			writer.close();

		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	protected boolean userExists(String userName) {
		File userFile = new File("/usr/local/Cellar/tomcat/7.0.39/libexec/webapps/LoginSystem/registeredUsers.txt");
		if(!userFile.exists() || !userFile.isFile()) {
			return false;
		} else {
			try { 
				FileReader reader = new FileReader(userFile);
			
				BufferedReader buffer = new BufferedReader(reader);
	
				ArrayList<String> users = new ArrayList<String>();
				
				while (true) 
				{
					String line = buffer.readLine();
					if (line == null) {
						buffer.close();
						break;
					}
					
					String userData[] = line.split("; ");
					users.add(userData[0]);
				}
				
				if(users.contains(userName)) {
					return true;
				} else {
					return false;
				}
			} catch(Exception e) {
				return true;
			}
		}
	}
}
