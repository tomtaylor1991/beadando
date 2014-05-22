package hu.unideb.etterem.model.interfaces;

/**
 * Az interfész reprezentál egy számlázási felelős elérési adatainak felületét.
 * 
 * @author Szabo Tamas
 *
 */
public interface SzamlazasiFelelos {
	
	/**
	 * Visszaaadja a számlázási felelős nevét.
	 * @return számlázási felelős neve
	 */
	public String getNev();
	
	/**
	 * Visszaaadja a számlázási felelős címét.
	 * 
	 * @return számlázási felelős címe
	 */
	public String getCim();
	
	/**
	 * Visszaaadja a számlázási felelős számlaszámát.
	 * 
	 * @return számlázási felelős számlaszáma
	 */
	public String getSzamlaSzam();
}
