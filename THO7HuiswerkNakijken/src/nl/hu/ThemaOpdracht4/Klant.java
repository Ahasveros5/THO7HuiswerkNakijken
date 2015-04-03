package nl.hu.ThemaOpdracht4;

import java.util.ArrayList;

public class Klant {

	private String voornaam, achternaam, geslacht, straat, huisnummer,
			postcode, plaats, email, emailb, wachtwoord, wachtwoordb;

	private ArrayList<Auto>autos = new ArrayList<Auto>();

	public Klant(String voornaam, String achternaam, String geslacht,
			String straat, String huisnummer, String postcode, String plaats,
			String email, String emailb, String wachtwoord, String wachtwoordb
			) {
		super();
		this.voornaam = voornaam; 
		this.achternaam = achternaam;
		this.geslacht = geslacht;
		this.straat = straat;
		this.huisnummer = huisnummer;
		this.postcode = postcode;
		this.plaats = plaats;
		this.email = email;
		this.emailb = emailb;
		this.wachtwoord = wachtwoord;
		this.wachtwoordb = wachtwoordb;

	}
	
	//Constructor voor de AutoServlet
	public Klant(String email){
		this.email = email;
	}
	
	public String getGeslacht() {
		return geslacht;
	}

	public void setGeslacht(String geslacht) {
		this.geslacht = geslacht;
	}

	public String getStraat() {
		return straat;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public String getHuisnummer() {
		return huisnummer;
	}

	public void setHuisnummer(String huisnummer) {
		this.huisnummer = huisnummer;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPlaats() {
		return plaats;
	}

	public void setPlaats(String plaats) {
		this.plaats = plaats;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailb() {
		return emailb;
	}

	public void setEmailb(String emailb) {
		this.emailb = emailb;
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}

	public String getWachtwoordb() {
		return wachtwoordb;
	}

	public void setWachtwoordb(String wachtwoordb) {
		this.wachtwoordb = wachtwoordb;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String anm) {
		achternaam = anm;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String vnm) {
		voornaam = vnm;
	}
	
	public boolean voegAutoToe(Auto a){
		boolean b = false;
		if(!heeftAuto(a.getKenteken())==true){
			b=true;
			autos.add(a);
		}
		return b;
		
	}
	private boolean heeftAuto(String e) {
		boolean b = false;
		for(Auto a: autos){
			if(a.getKenteken().equals(e)){
				b=true;
			}
		}return b;
	}
}
