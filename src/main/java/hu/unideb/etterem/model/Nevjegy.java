package hu.unideb.etterem.model;

/**
 *  Az osztály reprezentál egy Étterem Névjegy kártyáját.
 * 
 * @author Szabo Tamas
 *
 */
public class Nevjegy {
	/**
	 * A {@code Nevjegy}-hez tartozó név.
	 */
	public String nev;
	
	/**
	 * Az {@code Etterem} nyitvatartása. 
	 */
	public String nyitvaTartas;
	
	/**
	 * Az {@code Etterem} rövid leírása.
	 */
	public String leiras;
	
	/**
	 * Az {@code Etterem} elérhetőségei. 
	 */
	public Elerhetoseg elerhetoseg;
	
	/**
	 * Visszaadja a {@code Nevjegy} String reprezentációját.
	 * 
	 * @return a {@code Nevjegy} String reprezentációja
	 */
	public String toString() {
		return "Nevjegy [nev=" + nev + ", nyitvaTartas=" + nyitvaTartas
				+ ", leiras=" + leiras + ", elerhetoseg=" + elerhetoseg + "]";
	}
}
