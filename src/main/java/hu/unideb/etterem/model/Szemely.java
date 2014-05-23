package hu.unideb.etterem.model;

import hu.unideb.etterem.model.interfaces.SzamlazasiFelelos;

/**
 *  Az osztály reprezentál egy ember adatait.
 * 
 * @author Szabo Tamas
 *
 */
public class Szemely  implements SzamlazasiFelelos{
	
	/**
	 * A {@code Szemely} neve.
	 */
	private String nev;
	
	/**
	 * A {@code Szemely} lakcíme.
	 */
	private String cim;
	
	/**
	 * A {@code Szemely} számlaszáma.
	 */
	private String szamlaSzam;
	
	/**
	 * Konstruktor {@code Szemely} objektum példányosításához.
	 * 
	 * @param nev a {@code Szemely} neve
	 * @param cim a {@code Szemely} címe
	 * @param szamlaSzam a {@code Szemely} számlaszáma
	 */
	public Szemely(String nev, String cim, String szamlaSzam) {
		this.nev = nev;
		this.cim = cim;
		this.szamlaSzam = szamlaSzam;
	}
	
	
	/**
	 * Visszaaadja a számlázási felelős nevét.
	 * @return számlázási felelős neve
	 */
	public String getNev() {
		return nev;
	}
	
	/**
	 * Visszaaadja a számlázási felelős címét.
	 * 
	 * @return számlázási felelős címe
	 */
	public String getCim() {
		return cim;
	}
	
	/**
	 * Visszaaadja a számlázási felelős számlaszámát.
	 * 
	 * @return számlázási felelős számlaszáma
	 */
	public String getSzamlaSzam() {
		return szamlaSzam;
	}

	/**
	 * Visszaadja a {@code Szemely} String reprezentációját.
	 * 
	 * @return a {@code Szemely} String reprezentációja
	 */
	public String toString() {
		return "Szemely [Szamlazasi nev=" + nev + ", cim=" + cim + ", szamlaSzam="
				+ szamlaSzam + "]";
	}
	
}
