package hu.unideb.etterem.model;

import hu.unideb.etterem.model.interfaces.Vasarolhato;

/**
 * Az osztály reprezentál egy Étel terméket amelyet meg lehet vásárolni.
 * 
 * @author Szabo Tamas
 *
 */
public class Etel implements Vasarolhato{
	
	/**
	 * Az étel ára.
	 */
	private int ar;
	
	/**
	 * Az étel neve.
	 */
	private String nev;
	
	/**
	 * Az étel leírása.
	 */
	private String leiras;
	
	/**
	 * Konstruktor {@code Etel} objektum példányosításához.
	 * 
	 * @param nev az {@code Etel} neve
	 * @param ar az {@code Etel} ára
	 * @param leiras az {@code Etel} rövid leírása, pl.: mit tartalmaz
	 */
	public Etel( String nev, int ar, String leiras) {
		this.ar = ar;
		this.nev = nev;
		this.leiras = leiras;
	}
	
	/**
	 * Konstruktor {@code Etel} objektum példányosításához.
	 */
	public Etel() {
		this.ar = 0;
		this.nev = "";
		this.leiras = "";
	}
	
	/**
	 * Visszaadja a vársárolt termék árát.
	 * 
	 * @return a vársárolt termék ára
	 */
	public int getAr() {
		return ar;
	}

	/**
	 * Beállítja az {@code Etel} árát.
	 * @param ar az {@code Etel} ára
	 */
	public void setAr(int ar) {
		this.ar = ar;
	}

	/**
	 * Visszaadja a vársárolt termék nevét.
	 * 
	 * @return a vársárolt termék neve
	 */
	public String getNev() {
		return nev;
	}

	/**
	 * Beállítja az {@code Etel} elnevezését.
	 * @param nev az {@code Etel} neve
	 */
	public void setNev(String nev) {
		this.nev = nev;
	}

	/**
	 * Visszaadja az {@code Etel} rövid leírását.
	 * 
	 * @return az {@code Etel} rövid leírása
	 */
	public String getLeiras() {
		return leiras;
	}

	/**
	 * Beállítja az {@code Etel} rövid leírását.
	 * 
	 * @param leiras az {@code Etel} rövid leírása
	 */
	public void setLeiras(String leiras) {
		this.leiras = leiras;
	}

	/**
	 * Visszaadja az objektum Hash kódját.
	 *
	 * @return az objektum Hash kivonata
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ar;
		result = prime * result + ((leiras == null) ? 0 : leiras.hashCode());
		result = prime * result + ((nev == null) ? 0 : nev.hashCode());
		return result;
	}

	/**
	 * Összehasonlít két {@code Etel}-t, hogy egyenlőek -e. Két {@code Etel} objektumot egyenlőnek tekintünk,
	 * ha megegyezik a nevüt, leírásuk és az áruk.
	 *
	 * @param obj az objektum amellyel össze szeretnénk hasonlítani
	 * @return <code>true</code> ha az objektumok egyenlőek, <code>false</code> különben
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etel other = (Etel) obj;
		if (ar != other.ar)
			return false;
		if (leiras == null) {
			if (other.leiras != null)
				return false;
		} else if (!leiras.equals(other.leiras))
			return false;
		if (nev == null) {
			if (other.nev != null)
				return false;
		} else if (!nev.equals(other.nev))
			return false;
		return true;
	}

	/**
	 * Visszaadja az {@code Etel} String reprezentációját.
	 * 
	 * @return az {@code Etel} String reprezentációja
	 * */
	@Override
	public String toString() {
		return "[ar=" + ar + ", nev=" + nev + ", leiras=" + leiras
				+ "]";
	}
	
	
}
