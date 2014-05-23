package hu.unideb.etterem.model;

/**
 * Az osztály reprezentálja az elérhetőségét egy {@code Szemely}-nek.
 * 
 * @author Szabo Tamas
 *
 */
public class Elerhetoseg {
	
	/**
	 * Az {@code Elerhetoseg} által tárolt e-mail cím.
	 */
	public String email;
	
	/**
	 * Az {@code Elerhetoseg} által tárolt mobil telefonszám.
	 */
	public String mobil;
	
	/**
	 * Az {@code Elerhetoseg}  által tárolt {@code Cim}.
	 */
	public Cim cim;
	
	/**
	 * Konstruktor {@code Elerhetoseg} objektum példányosításához.
	 * 
	 * @param cim az {@code Elerhetoseg}  által tárolni kívánt email cím
	 * @param email az {@code Elerhetoseg}  által tárolni kívánt mobil telefonszám
	 * @param mobil az {@code Elerhetoseg}  által tárolni kívánt {@code Cim}
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
	 * Visszaadja az {@code Elerhetoseg} String reprezentációját.
	 * 
	 * @return az {@code Elerhetoseg} String reprezentációja
	 * */
	@Override
	public String toString() {
		return "Elerhetoseg [email=" + email + ", mobil=" + mobil + cim + "]";
	}
	
}
