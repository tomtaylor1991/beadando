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
	 * A személy neve.
	 */
	private String nev;
	
	/**
	 * A személy lakcíme.
	 */
	private String cim;
	
	/**
	 * A személy számlaszáma.
	 */
	private String szamlaSzam;
	
	/**
	 * Konstruktor {@code Szemely} objektum példányosításához.
	 * 
	 * @param nev a személy neve
	 * @param cim a személy címe
	 * @param szamlaSzam a személy számlaszáma
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
	 * Visszaadja a személy String reprezentációját.
	 * 
	 * @return a személy String reprezentációja
	 */
	public String toString() {
		return "Szemely [Szamlazasi nev=" + nev + ", cim=" + cim + ", szamlaSzam="
				+ szamlaSzam + "]";
	}
	
}
