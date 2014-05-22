package hu.unideb.etterem.model.interfaces;

/**
 * Az interfész felelős egy rendelés végösszeg kiszámolási szabályainak meghatározásáért.
 * 
 * @author Szabo Tamas
 *
 */
public interface VegosszegKalkulatorSzabaly {
	
	/**
	 * Visszaad egy szorzószámot, amellyel az egész összeget meg kell szorozni.
	 * 
	 * @return egy szorzószám
	 */
	double getSzorzo();
	
	/**
	 * Visszaad egy egész számot, amelyet az összeghez teljes egészében hozzá kell adni.
	 * 
	 * @return egy egész szám, amelyet az összeghez teljes egészében hozzá kell adni
	 */
	int getBonuszOsszeg();
}
