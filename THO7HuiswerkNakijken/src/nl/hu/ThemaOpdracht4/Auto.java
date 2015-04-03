package nl.hu.ThemaOpdracht4;

import java.util.Date;

public class Auto {

	private String merk, type, kleur, carrosserie, kenteken, transmissie, brandstof, bouwjaar, bijzonderheden;
	private Date aanschafdatum, apk;
	private int aantalDeuren;
	
	public Auto(String merk, String type, String kleur, String carrosserie,
			String kenteken, String transmissie, String brandstof,
			String bouwjaar, String bijzonderheden, Date aanschafdatum, Date apk, int aantaldeuren) {
		super();
		this.merk = merk;
		this.type = type;
		this.kleur = kleur;
		this.carrosserie = carrosserie;
		this.kenteken = kenteken;
		this.transmissie = transmissie;
		this.brandstof = brandstof;
		this.bouwjaar = bouwjaar;
		this.bijzonderheden = bijzonderheden;
		this.aanschafdatum = aanschafdatum;
		this.apk = apk;
		this.aantalDeuren = aantaldeuren;
	}
	public String getMerk() {
		return merk;
	}
	public void setMerk(String merk) {
		this.merk = merk;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKleur() {
		return kleur;
	}
	public void setKleur(String kleur) {
		this.kleur = kleur;
	}
	public String getCarrosserie() {
		return carrosserie;
	}
	public void setCarrosserie(String carrosserie) {
		this.carrosserie = carrosserie;
	}
	public String getKenteken() {
		return kenteken;
	}
	public void setKenteken(String kenteken) {
		this.kenteken = kenteken;
	}
	public String getTransmissie() {
		return transmissie;
	}
	public void setTransmissie(String transmissie) {
		this.transmissie = transmissie;
	}
	public String getBrandstof() {
		return brandstof;
	}
	public void setBrandstof(String brandstof) {
		this.brandstof = brandstof;
	}
	public String getBouwjaar() {
		return bouwjaar;
	}
	public void setBouwjaar(String bouwjaar) {
		this.bouwjaar = bouwjaar;
	}
	public String getBijzonderheden() {
		return bijzonderheden;
	}
	public void setBijzonderheden(String bijzonderheden) {
		this.bijzonderheden = bijzonderheden;
	}
	public Date getAanschafdatum() {
		return aanschafdatum;
	}
	public void setAanschafdatum(Date aanschafdatum) {
		this.aanschafdatum = aanschafdatum;
	}
	public Date getApk() {
		return apk;
	}
	public void setApk(Date apk) {
		this.apk = apk;
	}
	
	
}
