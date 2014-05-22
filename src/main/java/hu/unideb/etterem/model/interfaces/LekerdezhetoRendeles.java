package hu.unideb.etterem.model.interfaces;

/**
 * Az interfész reprezentál egy rendelés végösszeg lekérdezésésnek felületét.
 * 
 * @author Szabo Tamas
 *
 */
public interface LekerdezhetoRendeles {
	
	/**
	 * Visszaadja a rendelés végösszegét.
	 * @return a rendelés végösszege
	 */
	int getVegOsszeg();
}
