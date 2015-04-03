package nl.hu.ThemaOpdracht4;

import java.util.ArrayList;

public class ATD {
	private String naam;
	ArrayList<Klant>klanten = new ArrayList<Klant>();
	public ATD(String naam) {
		super();
		this.naam = naam;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public ArrayList<Klant> getKlanten() {
		return klanten;
	}

	public void setKlanten(ArrayList<Klant> klanten) {
		this.klanten = klanten;
	}
	
	public boolean voegKlantToe(Klant k){
		boolean b = false;
		if(!heeftAuto(k.getEmail())==true){
			b=true;
			klanten.add(k);
		}
		return b;
		
	}
	private boolean heeftAuto(String e) {
		boolean b = false;
		for(Klant k: klanten){
			if(k.getEmail().equals(e)){
				b=true;
			}
		}return b;
	}
}
