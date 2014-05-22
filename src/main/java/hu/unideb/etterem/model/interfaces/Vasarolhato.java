package hu.unideb.etterem.model.interfaces;

/**
 * Az interfész reprezentál egy várárlást amelyen keresztül megtudhajuk a vásárolt termék nevét és összegét.
 * 
 * @author Szabo Tamas
 *
 */
public interface Vasarolhato {
	
	/**
	 * Visszaadja a vársárolt termék árát.
	 * 
	 * @return a vársárolt termék ára
	 */
	int getAr();
	
	/**
	 * Visszaadja a vársárolt termék nevét.
	 * 
	 * @return a vársárolt termék neve
	 */
	String getNev();
}
