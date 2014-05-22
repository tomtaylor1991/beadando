package hu.unideb.etterem.model;

/**
 *  Az osztály reprezentál egy Étterem Névjegy kártyáját.
 * 
 * @author Szabo Tamas
 *
 */
public class Nevjegy {
	/**
	 * A névjegyhez tartozó név.
	 */
	public String nev;
	
	/**
	 * Az étterem nyitvatartása. 
	 */
	public String nyitvaTartas;
	
	/**
	 * Az étterem rövid leírása.
	 */
	public String leiras;
	
	/**
	 * Az étterem elérhetőségei. 
	 */
	public Elerhetoseg elerhetoseg;
	
	/**
	 * Visszaadja a névjegy String reprezentációját.
	 * 
	 * @return a névjegy String reprezentációja
	 */
	public String toString() {
		return "Nevjegy [nev=" + nev + ", nyitvaTartas=" + nyitvaTartas
				+ ", leiras=" + leiras + ", elerhetoseg=" + elerhetoseg + "]";
	}
}
