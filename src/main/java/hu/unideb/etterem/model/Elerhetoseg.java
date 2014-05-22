package hu.unideb.etterem.model;

/**
 * Az osztály reprezentál egy elérhetőségét egy személynek.
 * 
 * @author Szabo Tamas
 *
 */
public class Elerhetoseg {
	
	/**
	 * Az elérhetőség által tárolt email cím.
	 */
	public String email;
	
	/**
	 * Az elérhetőség által tárolt mobil telefonszám.
	 */
	public String mobil;
	
	/**
	 * Az elérhetőség által tárolt cím.
	 */
	public Cim cim;
	
	/**
	 * Konstruktor {@code Elerhetoseg} objektum példányosításához.
	 * 
	 * @param cim elérhetőség által tárolt email cím
	 * @param email elérhetőség által tárolt mobil telefonszám
	 * @param mobil elérhetőség által tárolt cím
	 */
	public Elerhetoseg(Cim cim, String email, String mobil) {
		this.email = email;
		this.mobil = mobil;
		this.cim = cim;
	}
	
	
	/**
	 * Konstruktor {@code Elerhetoseg} objektum példányosításához.
	 */
	public Elerhetoseg() {
	}

	/**
	 * Visszaadja az Elerhetoseg String reprezentációját.
	 * 
	 * @return az Elerhetoseg String reprezentációja
	 * */
	@Override
	public String toString() {
		return "Elerhetoseg [email=" + email + ", mobil=" + mobil + cim + "]";
	}
	
}
