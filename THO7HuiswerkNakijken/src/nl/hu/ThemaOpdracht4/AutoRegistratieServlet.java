package nl.hu.ThemaOpdracht4;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AutoRegistratieServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String merk = req.getParameter("invoer_merk");
		String type = req.getParameter("invoer_auto_type");
		String kleur = req.getParameter("invoer_kleur");
		int aantaldeuren = Integer.parseInt(req.getParameter("aantal_deuren"));
		String carrosserie = req.getParameter("carrosserie_type");
		String kenteken = req.getParameter("invoer_kenteken");
		String serienr = req.getParameter("invoer_serienummer");
		String transmissie = req.getParameter("invoer_transmissie");
		String brandstof = req.getParameter("invoer_brandstof");
		String bouwjaar = req.getParameter("Bouwjaar_Year");
		String aanschafmaand = req.getParameter("Aanschaf_Month");
		String aanschafdag = req.getParameter("Aanschaf_Day");
		String aanschafjaar = req.getParameter("Aanschaf_Year");
		String aanschafdatum = aanschafdag + " - " + aanschafmaand + " - "
				+ aanschafjaar;
		String apkmaand = req.getParameter("APK_tot_Month");
		String apkdag = req.getParameter("APK_tot_Day");
		String apkjaar = req.getParameter("APK_tot_Year");
		String apkdatum = apkdag + " - " + apkmaand + " - " + apkjaar;
		String bijz = req.getParameter("invoer_bijzonderheden");

		SimpleDateFormat sf = new SimpleDateFormat("dd, MM, yyyy");
		Date ASdate = new Date();
		Date APKdate = new Date();
		try {
			ASdate = sf.parse(aanschafdatum);
			APKdate = sf.parse(apkdatum);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = null;

		Object o = req.getSession().getAttribute("userEmail");
		Klant k = (Klant) o;

		if (merk != "" && type != "" && kleur != "" && carrosserie != ""
				&& kenteken != "" && serienr != "" && transmissie != ""
				&& brandstof != "" && bouwjaar != "" && ASdate != null
				&& APKdate != null) {

			Auto a = new Auto(merk, type, kleur, carrosserie, kenteken,
					transmissie, brandstof, bouwjaar, bijz, ASdate, APKdate,
					aantaldeuren);
			k.voegAutoToe(a);

			rd = req.getRequestDispatcher("Klantpagina.jsp");
		} else {
			req.setAttribute("msgs", "Er zijn enkele velden niet ingevoerd!");
			rd = req.getRequestDispatcher("auto_registratie.jsp");
		}
		rd.forward(req, resp);
	}
}
