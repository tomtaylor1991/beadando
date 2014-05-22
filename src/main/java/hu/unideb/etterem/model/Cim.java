package hu.unideb.etterem.model;

/**
 * Az osztály reprezentál egy címet.
 * 
 * @author Szabó Tamás
 *
 */
public class Cim {
	
	/**
	 *A címhez tartozó ország. 
	 */
	public String orszag;
	
	/**
	 * A címhez tartozó város.
	 */
	public String varos;
	
	/**
	 *A címhez tartozó utca név. 
	 */
	public String utca;
	
	/**
	 *A címhez tartozó házszám. 
	 */
	public int hazSzam;
	
	
	/**
	 * Konstruktor {@code Cim} objektum példányosításához.
	 * 
	 * @param orszag a címhez tartozó ország
	 * @param varos a címhez tartozó város
	 * @param utca a címhez tartozó utca név
	 * @param hazSzam a címhez tartozó házszám 
	 */
	public Cim(String orszag, String varos, String utca, int hazSzam) {
		this.orszag = orszag;
		this.varos = varos;
		this.utca = utca;
		this.hazSzam = hazSzam;
	}

	/**
	 * Visszaadja a {@code Cim} String reprezentációját.
	 * 
	 * @return a {@code Cim} String reprezentációja
	 * */
	public String toString() {
		return "Cim: "+orszag+" "+varos+" "+utca+" "+hazSzam+".";
	}
}
